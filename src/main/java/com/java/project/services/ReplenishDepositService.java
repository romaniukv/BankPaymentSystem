package com.java.project.services;

import com.java.project.model.dao.DepositReplenishmentDAO;
import com.java.project.model.dao.TransactionDAO;
import com.java.project.model.domain.DepositReplenishment;
import com.java.project.model.domain.Transaction;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

public class ReplenishDepositService {

    private static final String PUT_MONEY_TO_ACCOUNT = "UPDATE deposit_accounts SET balance = balance + ?, " +
            "amount = amount + ? WHERE number = ?";

    public boolean replenishDeposit(long senderAccountNumber, long receiverAccountNumber, BigDecimal amount) {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(PUT_MONEY_TO_ACCOUNT);
            ps.setBigDecimal(1, amount);
            ps.setBigDecimal(2, amount);
            ps.setLong(3,receiverAccountNumber);
            ps.execute();
            System.out.println(new Date());
            System.out.println(new GregorianCalendar().getTime());
            DepositReplenishment depositReplenishment = new DepositReplenishment(senderAccountNumber,
                    receiverAccountNumber, amount, new GregorianCalendar().getTime());
            if (new DepositReplenishmentDAO().create(depositReplenishment)) {
                connection.commit();
            }
        } catch (SQLException e) {
            DBConnection.rollbackConnection(connection);
            return false;
        } finally {
            DBConnection.closeConnection(connection);
        }
        return true;
    }
}
