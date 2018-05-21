package com.java.project.model.dao;

import com.java.project.model.domain.AccountStatus;
import com.java.project.model.domain.DepositAccount;
import com.java.project.services.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DepositAccountDAO extends AbstractDAO<DepositAccount> {

    private static final String SELECT_OPENED_ACCOUNT_BY_USER_ID = "SELECT * FROM deposit_accounts WHERE user_id = ? AND status = 'OPENED'";

    public DepositAccountDAO() {
        super("SELECT * FROM deposit_accounts;",
                "INSERT INTO deposit_accounts (balance, number, user_id, amount, rate, term, status, expiration_date) " +
                        " VALUES(?, ?, ?, ?, ?, ?, ?, ?);",
                "UPDATE deposit_accounts SET balance = ?, number = ?, user_id = ?, amount = ?, rate = ?, term = ?, " +
                        "status = ?, expiration_date = ? WHERE id = ?;",
                "SELECT * FROM deposit_accounts WHERE id = ?",
                "DELETE FROM deposit_accounts WHERE id = ?;",
                new String[][]{{"balance", "balance"},
                        {"number", "number"},
                        {"userId", "user_id"},
                        {"amount", "amount"},
                        {"rate", "rate"},
                        {"term", "term"},
                        {"status", "status"},
                        {"expirationDate", "expiration_date"}},
                DepositAccount.class);
    }

    public List<DepositAccount> selectByUserId(int userId) {
        List<DepositAccount> depositAccounts = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_OPENED_ACCOUNT_BY_USER_ID);
            ps.setInt(1,userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                depositAccounts.add(new DepositAccount(rs.getInt(1), rs.getLong(2),
                        rs.getBigDecimal(3), rs.getInt(4), rs.getBigDecimal(5),
                        rs.getBigDecimal(6), rs.getInt(7),
                        AccountStatus.valueOf(rs.getString(8)), rs.getDate(9)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return depositAccounts;
    }


}
