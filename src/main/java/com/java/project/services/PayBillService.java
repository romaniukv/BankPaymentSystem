package com.java.project.services;

import com.java.project.model.dao.PaymentDAO;
import com.java.project.model.domain.Payment;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;


public class PayBillService {

    public boolean payBill(String senderName, long senderAccount, String receiverName, long receiverAccount, BigDecimal amount, String purpose) {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean flag = new TransferMoneyService().withdrawMoneyFromAccount(connection, senderAccount, amount);
            if (!flag) {
                return false;
            }

            Payment payment = new Payment(senderName, senderAccount, receiverName, receiverAccount, amount, purpose,
                    new GregorianCalendar().getTime());

            if (new PaymentDAO().create(payment)) {
                System.out.println("no payment");
                connection.commit();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            DBConnection.rollbackConnection(connection);
            return false;
        } finally {
            DBConnection.closeConnection(connection);
        }
        return false;
    }
}
