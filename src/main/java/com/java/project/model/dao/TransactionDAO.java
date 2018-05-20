package com.java.project.model.dao;

import com.java.project.model.domain.Transaction;
import com.java.project.services.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO extends AbstractDAO<Transaction> {

    private static final String SELECT_ALL_BY_ACCOUNT_NUMBER = "SELECT sender_account_number, receiver_account_number, " +
            "amount, date FROM transactions WHERE " +
            "sender_account_number = ? OR receiver_account_number = ? ORDER BY date DESC LIMIT 10;";

    public TransactionDAO() {
        super("SELECT * FROM transactions;",
                "INSERT INTO transactions (sender_account_number, receiver_account_number, amount, date) VALUES(?, ?, ?, ?);",
                "UPDATE transactions SET sender_account_number = ?, receiver_account_number = ?, amount = ?, date = ? WHERE id = ?;",
                "SELECT * FROM transactions WHERE id = ?;",
                "DELETE FROM transactions WHERE id = ?;",
                new String[][]{{"senderAccountNumber", "sender_account_number"},
                        {"receiverAccountNumber", "receiver_account_number"},
                        {"amount", "amount"},
                        {"date", "date"}},
                Transaction.class);
    }

    public List<Transaction> selectAllByAccountNumber(long accountNumber) {
        List<Transaction> transactions = new ArrayList<>();
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL_BY_ACCOUNT_NUMBER);
            ps.setLong(1, accountNumber);
            ps.setLong(2, accountNumber);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                transactions.add(new Transaction(rs.getLong(1), rs.getLong(2),
                        rs.getBigDecimal(3), rs.getTimestamp(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
