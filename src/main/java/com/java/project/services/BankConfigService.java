package com.java.project.services;

import com.java.project.model.domain.DepositAccount;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BankConfigService {

    private static final String SELECT_CREDIT_LIMITS = "SELECT * FROM credit_limits";

    private static final String GET_LAST_ACCOUNT_NUMBER = "SELECT number FROM accounts_numbers " +
            "WHERE id=(SELECT MAX(id) FROM accounts_numbers);";

    private static final String SAVE_NEW_ACCOUNT_NUMBER = "INSERT INTO accounts_numbers (number) VALUE (?)";

    private static final String SELECT_AVAILABLE_DEPOSITS = "SELECT id, name, term, rate FROM deposit_catalog" +
            " WHERE available = 1";

    private static final String FIND_DEPOSIT_IN_CATALOG = "SELECT id, name, term, rate FROM deposit_catalog " +
            "WHERE id = ?";

    public Map<BigDecimal, BigDecimal> selectCreditLimits() {
        Map<BigDecimal, BigDecimal> creditLimits = new TreeMap<>();
        try (Connection connection = DBConnection.getInstance().getConnection()) {
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

    public long getNewAccountNumber() {
        long accountNumber = 0;
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
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

    public List<DepositAccount> selectAvailableDepositAccountsFromCatalog() {
        List<DepositAccount> depositAccounts = new ArrayList<>();
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SELECT_AVAILABLE_DEPOSITS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                depositAccounts.add(new DepositAccount(rs.getInt(1), rs.getString(2),
                rs.getInt(3), rs.getBigDecimal(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return depositAccounts;
    }

    public DepositAccount findDepositInCatalog(int id) {
        DepositAccount depositAccount = null;
        try(Connection connection = DBConnection.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(FIND_DEPOSIT_IN_CATALOG);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                depositAccount = new DepositAccount(rs.getInt(1), rs.getString(2),
                        rs.getInt(3), rs.getBigDecimal(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return depositAccount;
    }
}
