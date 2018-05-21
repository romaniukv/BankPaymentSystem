package com.java.project.model.dao;

import com.java.project.model.domain.Transaction;
import com.java.project.services.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class TransactionDAO extends AbstractDAO<Transaction> {

    private static final String SELECT_ALL_BY_ACCOUNT_NUMBER = "SELECT id, sender_account_number, receiver_account_number, " +
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
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL_BY_ACCOUNT_NUMBER);
            ps.setLong(1, accountNumber);
            ps.setLong(2, accountNumber);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                transactions.add(new Transaction(rs.getInt(1),rs.getLong(2),
                        rs.getLong(3), rs.getBigDecimal(4), rs.getTimestamp(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    private static final String SELECT_BALANCE_BY_NUMBER = "SELECT balance FROM credit_accounts WHERE number = ? AND status = 'OPENED'";

    private static final String WITHDRAW_MONEY_FROM_ACCOUNT = "UPDATE credit_accounts SET balance = balance - ? WHERE number = ?";

    private static final String PUT_MONEY_TO_ACCOUNT = "UPDATE credit_accounts SET balance = balance + ? WHERE number = ?";


    public boolean transferMoney(long fromAccount, long toAccount, BigDecimal amount) {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            if (!withdrawMoneyFromAccount(connection, fromAccount, amount)) {
                return false;
            }

            PreparedStatement ps = connection.prepareStatement(PUT_MONEY_TO_ACCOUNT);
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
            DBConnection.rollbackConnection(connection);
            return false;
        } finally {
            DBConnection.closeConnection(connection);
        }
        return true;
    }

    public boolean withdrawMoneyFromAccount(Connection connection, long accountNumber, BigDecimal amount) {
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_BALANCE_BY_NUMBER);
            ps.setLong(1, accountNumber);
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
            ps.setLong(2,accountNumber);
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
