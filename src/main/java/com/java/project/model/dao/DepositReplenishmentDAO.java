package com.java.project.model.dao;

import com.java.project.model.domain.DepositReplenishment;
import com.java.project.services.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepositReplenishmentDAO extends AbstractDAO<DepositReplenishment> {

    private static final String SELECT_ALL_BY_ACCOUNT_NUMBER = "SELECT id,  sender_account_number, receiver_account_number, " +
            "amount, date FROM deposit_replenishments WHERE " +
            "receiver_account_number = ? ORDER BY date DESC LIMIT 10;";

    public DepositReplenishmentDAO() {
        super("SELECT * FROM deposit_replenishments;",
                "INSERT INTO deposit_replenishments (sender_account_number, receiver_account_number, amount, date) VALUES(?, ?, ?, ?);",
                "UPDATE deposit_replenishments SET sender_account_number = ?, receiver_account_number = ?, amount = ?, date = ? WHERE id = ?;",
                "SELECT * FROM deposit_replenishments WHERE id = ?;",
                "DELETE FROM deposit_replenishments WHERE id = ?;",
                new String[][]{{"senderAccountNumber", "sender_account_number"},
                        {"receiverAccountNumber", "receiver_account_number"},
                        {"amount", "amount"},
                        {"date", "date"}},
                DepositReplenishment.class);
    }

    public List<DepositReplenishment> selectAllByAccountNumber(long accountNumber) {
        List<DepositReplenishment> depositReplenishments = new ArrayList<>();
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL_BY_ACCOUNT_NUMBER);
            ps.setLong(1, accountNumber);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                depositReplenishments.add(new DepositReplenishment(rs.getInt(1),rs.getLong(2),
                        rs.getLong(3), rs.getBigDecimal(4), rs.getTimestamp(5)));
            }
        } catch (SQLException e) {
        }
        return depositReplenishments;
    }
}
