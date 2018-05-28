package com.java.project.services.impl;

import com.java.project.factory.DAOFactory;
import com.java.project.model.dao.CreditAccountDAO;
import com.java.project.model.domain.CreditAccount;
import com.java.project.services.CreditAccountService;
import com.java.project.services.DBConnection;
import com.java.project.services.generic.impl.GenericServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementation of credit account service.
 */
public class CreditAccountServiceImpl extends GenericServiceImpl<CreditAccount> implements CreditAccountService {

    private static final String SELECT_BALANCE_BY_NUMBER = "SELECT balance FROM credit_accounts WHERE number = ? AND status = 'OPENED'";

    private static final String WITHDRAW_MONEY_FROM_ACCOUNT = "UPDATE credit_accounts SET balance = balance - ? WHERE number = ?";

    private static final Logger logger = LogManager.getLogger(CreditAccountServiceImpl.class);

    private CreditAccountDAO creditAccountDAO;

    public CreditAccountServiceImpl(CreditAccountDAO creditAccountDAO) {
        this.creditAccountDAO = creditAccountDAO;
        setDAOImpl(creditAccountDAO);
    }

    public CreditAccountServiceImpl() {
        this(DAOFactory.getCreditAccountDAO());
    }

    @Override
    public List<CreditAccount> selectNewAccounts() {
        Connection connection = null;
        List<CreditAccount> creditAccounts = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            creditAccountDAO.setConnection(connection);
            creditAccounts = creditAccountDAO.selectNewAccounts();
            connection.commit();
        } catch (SQLException e) {
            logger.error(e);
            DBConnection.rollbackAndCloseConnection(connection);
        }
        finally {
            DBConnection.closeConnection(connection);
        }
        return creditAccounts;
    }

    @Override
    public CreditAccount selectByUserId(int userId) {
        Connection connection = null;
        CreditAccount creditAccount = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            creditAccountDAO.setConnection(connection);
            creditAccount = creditAccountDAO.selectByUserId(userId);
            connection.commit();
        } catch (SQLException e) {
            logger.error(e);
            DBConnection.rollbackAndCloseConnection(connection);
        }
        finally {
            DBConnection.closeConnection(connection);
        }
        return creditAccount;
    }

    @Override
    public boolean withdrawMoneyFromAccount(long accountNumber, BigDecimal amount) {
        Connection connection = null;
        PreparedStatement selectBalance = null;
        PreparedStatement withdrawMoney = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            selectBalance = connection.prepareStatement(SELECT_BALANCE_BY_NUMBER);
            withdrawMoney = connection.prepareStatement(WITHDRAW_MONEY_FROM_ACCOUNT);

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
            if (selectBalance != null) {
                try {
                    selectBalance.close();
                } catch (SQLException e1) {
                    logger.error(e1);
                }
            }
            if (withdrawMoney != null) {
                try {
                    withdrawMoney.close();
                } catch (SQLException e1) {
                    logger.error(e1);
                }
            }
            DBConnection.rollbackAndCloseConnection(connection);
            return false;
        }
        finally {
            DBConnection.closeConnection(connection);
        }
        return true;
    }
}
