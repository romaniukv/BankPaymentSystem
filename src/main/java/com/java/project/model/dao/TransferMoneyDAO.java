package com.java.project.model.dao;

import com.java.project.model.entities.Transaction;
import com.java.project.utils.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;
import java.util.GregorianCalendar;

public class TransferMoneyDAO {

    private static final String SELECT_BALANCE_BY_NUMBER = "SELECT balance FROM credit_accounts WHERE number = ? AND status = 'OPENED'";

    private static final String WITHDRAW_MONEY_FROM_ACCOUNT = "UPDATE credit_accounts SET balance = balance - ? WHERE number = ?";

    private static final String PUT_MONEY_TO_ACCOUNT = "UPDATE credit_accounts SET balance = balance + ? WHERE number = ?";


    public boolean transferMoney(long fromAccount, long toAccount, BigDecimal amount) {
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(SELECT_BALANCE_BY_NUMBER);
            ps.setLong(1, fromAccount);
            ResultSet rs = ps.executeQuery();
            BigDecimal fromBalance;
            if (rs.next()) {
                fromBalance = rs.getBigDecimal(1);
            }
            else return false;
            ps.close();

            if (fromBalance.compareTo(amount) <= 0) {
                return false;
            }

            ps = connection.prepareStatement(WITHDRAW_MONEY_FROM_ACCOUNT);
            ps.setBigDecimal(1, amount);
            ps.setLong(2,fromAccount);
            ps.execute();
            ps.close();

            ps = connection.prepareStatement(PUT_MONEY_TO_ACCOUNT);
            ps.setBigDecimal(1, amount);
            ps.setLong(2,toAccount);
            ps.execute();
            System.out.println(new Date());
            System.out.println(new GregorianCalendar().getTime());
            Transaction transaction = new Transaction(fromAccount, toAccount, amount, new GregorianCalendar().getTime());
            if (new TransactionDAO().create(transaction)) {
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    return false;
                }
            }
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
        return true;
    }

}
