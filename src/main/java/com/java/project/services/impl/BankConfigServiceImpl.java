package com.java.project.services.impl;

import com.java.project.model.domain.DepositAccount;
import com.java.project.services.BankConfigService;
import com.java.project.services.DBConnection;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Implementation of BankConfigService.
 */
public class BankConfigServiceImpl implements BankConfigService {

    private static final Logger logger = LogManager.getLogger(BankConfigServiceImpl.class);


    private static final String SELECT_CREDIT_LIMITS = "SELECT * FROM credit_limits";

    private static final String GET_LAST_ACCOUNT_NUMBER = "SELECT number FROM accounts_numbers " +
            "WHERE id=(SELECT MAX(id) FROM accounts_numbers);";

    private static final String SAVE_NEW_ACCOUNT_NUMBER = "INSERT INTO accounts_numbers (number) VALUE (?)";

    private static final String SELECT_AVAILABLE_DEPOSITS = "SELECT id, name, term, rate FROM deposit_catalog" +
            " WHERE available = 1";

    private static final String FIND_DEPOSIT_IN_CATALOG = "SELECT id, name, term, rate FROM deposit_catalog " +
            "WHERE id = ?";

    private static final String ADD_DEPOSIT_TO_CATALOG = "INSERT INTO deposit_catalog (name, term, rate, available) " +
            "VALUES(?, ?, ?, ?)";

    private static final String REMOVE_DEPOSIT_FROM_CATALOG = "DELETE FROM deposit_catalog WHERE id = ?;";

    private static final String UPDATE_DEPOSIT_IN_CATALOG = "UPDATE deposit_catalog SET name = ?, rate = ?," +
            "term = ? WHERE id = ?";

    @Override
    public Map<BigDecimal, BigDecimal> selectCreditLimits() {
        Map<BigDecimal, BigDecimal> creditLimits = new TreeMap<>();
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SELECT_CREDIT_LIMITS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                creditLimits.put(rs.getBigDecimal("limit"), rs.getBigDecimal("rate"));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return creditLimits;
    }

    @Override
    public long getNewAccountNumber() {
        long accountNumber = 0;
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(GET_LAST_ACCOUNT_NUMBER);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                accountNumber = rs.getLong(1);
                accountNumber++;
            }
            ps = connection.prepareStatement(SAVE_NEW_ACCOUNT_NUMBER);
            ps.setLong(1, accountNumber);
            ps.execute();
            connection.commit();
        } catch (SQLException e) {
            DBConnection.rollbackAndCloseConnection(connection);
            logger.error(e);
        }
        finally {
            DBConnection.closeConnection(connection);
        }
        return accountNumber;
    }

    @Override
    public List<DepositAccount> selectAvailableDepositAccountsFromCatalog() {
        List<DepositAccount> depositAccounts = new ArrayList<>();
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SELECT_AVAILABLE_DEPOSITS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                depositAccounts.add(new DepositAccount(rs.getInt(1), rs.getString(2),
                rs.getInt(3), rs.getBigDecimal(4)));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return depositAccounts;
    }

    @Override
    public DepositAccount findDepositInCatalog(int id) {
        DepositAccount depositAccount = null;
        try(Connection connection = DBConnection.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(FIND_DEPOSIT_IN_CATALOG);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                depositAccount = new DepositAccount(rs.getInt(1), rs.getString(2),
                        rs.getInt(3), rs.getBigDecimal(4));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return depositAccount;
    }

    @Override
    public boolean addDepositToCatalog(DepositAccount deposit) {
        Connection connection = null;
        boolean flag;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(ADD_DEPOSIT_TO_CATALOG);
            ps.setString(1, deposit.getName());
            ps.setInt(2, deposit.getTerm());
            ps.setBigDecimal(3, deposit.getRate());
            ps.setBoolean(4, true);
            ps.execute();
            connection.commit();
            flag = true;
        } catch (SQLException e) {
            logger.error(e);
            DBConnection.rollbackAndCloseConnection(connection);
            flag = false;
        }
        finally {
            DBConnection.closeConnection(connection);
        }
        return flag;
    }

    @Override
    public void removeDepositFromCatalog(int id) {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(REMOVE_DEPOSIT_FROM_CATALOG);
            ps.setInt(1, id);
            ps.execute();
            connection.commit();
        } catch (SQLException e) {
            logger.error(e);
            DBConnection.rollbackAndCloseConnection(connection);
        }
        finally {
            DBConnection.closeConnection(connection);
        }
    }

    @Override
    public boolean updateDepositInCatalog(String name, int term, BigDecimal rate, int id) {
        boolean flag;
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(UPDATE_DEPOSIT_IN_CATALOG);
            ps.setString(1, name);
            ps.setBigDecimal(2, rate);
            ps.setInt(3, term);
            ps.setInt(4, id);
            ps.executeUpdate();
            flag = true;
            connection.commit();
        } catch (SQLException e) {
            logger.error(e);
            flag = false;
            DBConnection.rollbackAndCloseConnection(connection);
        }
        finally {
            DBConnection.closeConnection(connection);
        }
        return flag;
    }
}
