package com.java.project.model.dao;

import com.java.project.model.entities.AccountStatus;
import com.java.project.model.entities.CreditAccount;
import com.java.project.utils.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreditAccountDAO extends AbstractDAO<CreditAccount> {

    private static final String SELECT_NEW_ACCOUNTS = "SELECT number, id, user_id, credit_limit, credit_rate " +
            "FROM credit_accounts WHERE status = ?";

    private static final String SELECT_OPENED_ACCOUNT_BY_USER_ID = "SELECT * FROM credit_accounts WHERE user_id = ? AND status = 'OPENED'";

    private static final String SELECT_BALANCE_BY_NUMBER = "SELECT balance FROM credit_accounts WHERE number = ? AND status = 'OPENED'";

    private static final String WITHDRAW_MONEY_FROM_ACCOUNT = "UPDATE credit_accounts SET balance = balance - ? WHERE number = ?";

    private static final String PUT_MONEY_TO_ACCOUNT = "UPDATE credit_accounts SET balance = balance + ? WHERE number = ?";

    public CreditAccountDAO() {
        super("SELECT * FROM credit_accounts;",
                "INSERT INTO credit_accounts (balance, number, user_id, expiration_date, credit_limit, indebtedness, accrued_interest, " +
                        "credit_rate, status) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);",
                "UPDATE credit_accounts SET balance = ?, number = ?, user_id = ?, expiration_date = ?, credit_limit = ?," +
                        " indebtedness = ?, accrued_interest = ?, credit_rate = ?, status = ? WHERE id = ?;",
                "SELECT * FROM credit_accounts WHERE id = ?",
                "DELETE FROM credit_accounts WHERE id = ?;",
                new String[][]{{"balance", "balance"},
                        {"number", "number"},
                        {"userId", "user_id"},
                        {"expirationDate", "expiration_date"},
                        {"limit", "credit_limit"},
                        {"indebtedness", "indebtedness"},
                        {"accruedInterest", "accrued_interest"},
                        {"creditRate", "credit_rate"},
                        {"status", "status"}},
                CreditAccount.class);
    }

    public List<CreditAccount> selectNewAccounts() {
        List<CreditAccount> creditAccounts = new ArrayList<>();
        try(Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SELECT_NEW_ACCOUNTS);
            ps.setString(1, AccountStatus.UNDER_CONSIDERATION.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                creditAccounts.add(new CreditAccount(rs.getLong(1), rs.getInt(2),
                        rs.getInt(3), rs.getBigDecimal(4), rs.getBigDecimal(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return creditAccounts;
    }

    public CreditAccount selectByUserId(int userId) {
        CreditAccount creditAccount = null;
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SELECT_OPENED_ACCOUNT_BY_USER_ID);
            ps.setInt(1,userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                creditAccount = new CreditAccount(rs.getInt(1), rs.getLong(2),
                        rs.getBigDecimal(3), rs.getInt(4), rs.getBigDecimal(5),
                        rs.getBigDecimal(6), rs.getBigDecimal(7), rs.getBigDecimal(8),
                        AccountStatus.valueOf(rs.getString(9)), rs.getDate(10));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return creditAccount;
    }

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

            connection.commit();
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
