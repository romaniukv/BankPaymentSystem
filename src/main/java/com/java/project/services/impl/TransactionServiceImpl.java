package com.java.project.services.impl;

import com.java.project.factory.DAOFactory;
import com.java.project.model.dao.TransactionDAO;
import com.java.project.model.domain.Transaction;
import com.java.project.services.DBConnection;
import com.java.project.services.TransactionService;
import com.java.project.services.generic.impl.GenericServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

/**
 * Implementation of transaction service.
 */
public class TransactionServiceImpl extends GenericServiceImpl<Transaction> implements TransactionService {

    private static final Logger logger = LogManager.getLogger(TransactionServiceImpl.class);

    private TransactionDAO transactionDAO;

    public TransactionServiceImpl(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
        setDAOImpl(transactionDAO);
    }

    public TransactionServiceImpl() {
        this(DAOFactory.getTransacionDAO());
    }

    @Override
    public List<Transaction> selectAllByAccountNumber(long accountNumber) {
        Connection connection = null;
        List<Transaction> transactions = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            transactionDAO.setConnection(connection);
            transactions = transactionDAO.selectAllByAccountNumber(accountNumber);
            connection.commit();
        } catch (SQLException e) {
            logger.error(e);
            DBConnection.rollbackAndCloseConnection(connection);
        }
        finally {
            DBConnection.closeConnection(connection);
        }
        return transactions;
    }

    @Override
    public boolean transferMoney(long fromAccount, long toAccount, BigDecimal amount) {
        Connection connection = null;
        boolean flag;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            transactionDAO.setConnection(connection);
            flag = transactionDAO.transferMoney(fromAccount, toAccount, amount);
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

    @Override
    public boolean withdrawMoneyFromAccount(long accountNumber, BigDecimal amount) {
        Connection connection = null;
        boolean flag;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            transactionDAO.setConnection(connection);
            flag = transactionDAO.withdrawMoneyFromAccount(accountNumber, amount);
            connection.commit();
        } catch (SQLException e) {
            flag = false;
            DBConnection.rollbackAndCloseConnection(connection);
        }
        finally {
            DBConnection.closeConnection(connection);
        }
        return flag;
    }
}
