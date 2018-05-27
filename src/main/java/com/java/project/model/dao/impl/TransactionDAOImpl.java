package com.java.project.model.dao.impl;

import com.java.project.factory.ServiceFactory;
import com.java.project.model.dao.TransactionDAO;
import com.java.project.model.dao.generic.impl.GenericDAOImpl;
import com.java.project.model.domain.Transaction;
import com.java.project.services.DBConnection;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Implementation of TransactionDAO
 */
public class TransactionDAOImpl extends GenericDAOImpl<Transaction> implements TransactionDAO {

    private static final Logger logger = LogManager.getLogger(TransactionDAOImpl.class);

    private static final String SELECT_ALL_BY_ACCOUNT_NUMBER = "SELECT id, sender_account_number, receiver_account_number, " +
            "amount, date FROM transactions WHERE " +
            "sender_account_number = ? OR receiver_account_number = ? ORDER BY date DESC LIMIT 10;";

    private static final String SELECT_BALANCE_BY_NUMBER = "SELECT balance FROM credit_accounts WHERE number = ? AND status = 'OPENED'";

    private static final String WITHDRAW_MONEY_FROM_ACCOUNT = "UPDATE credit_accounts SET balance = balance - ? WHERE number = ?";

    private static final String PUT_MONEY_TO_ACCOUNT = "UPDATE credit_accounts SET balance = balance + ? WHERE number = ?";


    /**
     * Passes query strings, name mapping and Transaction.class to constructor in superclass.
     */
    public TransactionDAOImpl() {
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

    @Override
    public List<Transaction> selectAllByAccountNumber(long accountNumber) {
        List<Transaction> transactions = new ArrayList<>();
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_BY_ACCOUNT_NUMBER)) {
            ps.setLong(1, accountNumber);
            ps.setLong(2, accountNumber);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                transactions.add(new Transaction(rs.getInt(1),rs.getLong(2),
                        rs.getLong(3), rs.getBigDecimal(4), rs.getTimestamp(5)));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return transactions;
    }

    @Override
    public boolean transferMoney(long fromAccount, long toAccount, BigDecimal amount) {
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement(PUT_MONEY_TO_ACCOUNT)) {
            if (!withdrawMoneyFromAccount(fromAccount, amount)) {
                return false;
            }
            ps.setBigDecimal(1, amount);
            ps.setLong(2,toAccount);
            ps.execute();
            Transaction transaction = new Transaction(fromAccount, toAccount, amount, new GregorianCalendar().getTime());
            if (ServiceFactory.getTransactionService().create(transaction)) {
                connection.commit();
            }
        } catch (SQLException e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean withdrawMoneyFromAccount(long accountNumber, BigDecimal amount) {
        Connection connection = getConnection();
        try (PreparedStatement selectBalance = connection.prepareStatement(SELECT_BALANCE_BY_NUMBER);
             PreparedStatement withdrawMoney = connection.prepareStatement(WITHDRAW_MONEY_FROM_ACCOUNT)) {

            selectBalance.setLong(1, accountNumber);
            ResultSet rs = selectBalance.executeQuery();
            BigDecimal fromBalance;
            if (rs.next()) {
                fromBalance = rs.getBigDecimal(1);
            }

            else return false;
            selectBalance.close();

            if (fromBalance.compareTo(amount) <= 0) {
                return false;
            }

            withdrawMoney.setBigDecimal(1, amount);
            withdrawMoney.setLong(2,accountNumber);
            withdrawMoney.execute();
            withdrawMoney.close();

        } catch (SQLException e) {
            logger.error(e);
            return false;
        }
        return true;
    }
}
