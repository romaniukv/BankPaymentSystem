package com.java.project.model.dao.impl;

import com.java.project.model.dao.PaymentDAO;
import com.java.project.model.dao.generic.impl.GenericDAOImpl;
import com.java.project.model.domain.Payment;
import com.java.project.services.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class PaymentDAOImpl extends GenericDAOImpl<Payment> implements PaymentDAO {

    private static final String SELECT_ALL_BY_ACCOUNT_NUMBER = "SELECT sender, sender_account_number, receiver, " +
            "receiver_account_number, amount, purpose, date FROM payments WHERE " +
            "sender_account_number = ? ORDER BY date DESC LIMIT 10;";

    public PaymentDAOImpl() {
        super("SELECT * FROM payments LIMIT 10;",
                "INSERT INTO payments (sender, sender_account_number, receiver, receiver_account_number, amount, purpose," +
                        " date) VALUES(?, ?, ?, ?, ?, ?, ?);",
                "UPDATE payments SET sender = ?, sender_account_number = ?, receiver = ?, amount = ?, purpose = ?, date = ?" +
                        " WHERE id = ?;",
                "SELECT * FROM payments WHERE id = ?;",
                "DELETE FROM payments WHERE id = ?;",
                new String[][]{{"senderFullName", "sender"},
                        {"senderAccountNumber", "sender_account_number"},
                        {"receiverFullName", "receiver"},
                        {"receiverAccountNumber", "receiver_account_number"},
                        {"amount", "amount"},
                        {"purpose", "purpose"},
                        {"date", "date"}},
                Payment.class);
    }

    @Override
    public List<Payment> selectAllByAccountNumber(long accountNumber) {
        List<Payment> payments = new ArrayList<>();
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_BY_ACCOUNT_NUMBER)) {
            ps.setLong(1, accountNumber);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                payments.add(new Payment(rs.getString(1), rs.getLong(2),
                        rs.getString(3), rs.getLong(4),
                        rs.getBigDecimal(5), rs.getString(6), rs.getTimestamp(7)));
            }
        } catch (SQLException e) {

        }
        return payments;
    }

    @Override
    public boolean payBill(String senderName, long senderAccount, String receiverName, long receiverAccount, BigDecimal amount, String purpose) {
        Connection connection = getConnection();
        boolean flag = new TransactionDAOImpl().withdrawMoneyFromAccount(connection, senderAccount, amount);
        if (!flag) {
            return false;
        }

        Payment payment = new Payment(senderName, senderAccount, receiverName, receiverAccount, amount, purpose,
                new GregorianCalendar().getTime());

        return create(payment);

    }
}
