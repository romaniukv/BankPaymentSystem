package com.epam.project.dao;

import com.epam.project.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BankConfigDAO {

    private static final String SELECT_CREDIT_LIMITS = "SELECT * FROM credit_limits";

    public static Map<Integer, Double> selectCreditLimits() {
        Map<Integer, Double> creditLimits = new TreeMap<>();
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SELECT_CREDIT_LIMITS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                creditLimits.put(rs.getInt("limit"), rs.getDouble("rate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return creditLimits;
    }
}
