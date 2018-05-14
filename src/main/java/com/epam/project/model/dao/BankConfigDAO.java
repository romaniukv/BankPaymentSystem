package com.epam.project.model.dao;

import com.epam.project.utils.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BankConfigDAO {

    private static final String SELECT_CREDIT_LIMITS = "SELECT * FROM credit_limits";
    private static final String GET_LAST_ACCOUNT_NUMBER = "SELECT number FROM accounts_numbers " +
            "WHERE id=(SELECT MAX(id) FROM accounts_numbers);";
    private static final String SAVE_NEW_ACCOUNT_NUMBER = "INSERT INTO accounts_numbers (number) VALUE (?)";

    public static Map<BigDecimal, BigDecimal> selectCreditLimits() {
        Map<BigDecimal, BigDecimal> creditLimits = new TreeMap<>();
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SELECT_CREDIT_LIMITS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                creditLimits.put(rs.getBigDecimal("limit"), rs.getBigDecimal("rate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return creditLimits;
    }

    public static long getNewAccountNumber() {
        long accountNumber = 0;
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(GET_LAST_ACCOUNT_NUMBER);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                accountNumber = rs.getLong(1);
                accountNumber++;
            }
            ps = connection.prepareStatement(SAVE_NEW_ACCOUNT_NUMBER);
            ps.setLong(1, accountNumber);
            ps.execute();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        }
        return accountNumber;
    }
}
