package com.java.project.model.dao.impl;

import com.java.project.model.dao.CreditAccountDAO;
import com.java.project.model.domain.CreditAccount;
import com.java.project.model.domain.User;
import com.java.project.services.DBConnection;
import entities.TestEntities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class TransactionDAOImplTest {

    @Before
    public void setUp() {
        DBConnection.setTestMode();
    }

    @After
    public void tearDown() {
        DBConnection.setApplicationMode();
    }

    @Test
    public void transferMoney() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.setConnection(connection);
        User user = TestEntities.getTestUser2();
        userDAO.create(user);

        CreditAccount senderAccountBefore = TestEntities.getTestCreditAccount();
        senderAccountBefore.setUserId(user.getId());
        CreditAccount receiverAccountBefore = TestEntities.getTestCreditAccount2();
        receiverAccountBefore.setUserId(user.getId());

        CreditAccountDAO creditAccountDAO = new CreditAccountDAOImpl();
        creditAccountDAO.setConnection(connection);
        creditAccountDAO.create(senderAccountBefore);
        creditAccountDAO.create(receiverAccountBefore);

        TransactionDAOImpl transactionDAO = new TransactionDAOImpl();
        transactionDAO.setConnection(connection);

        BigDecimal amount = new BigDecimal(3567).movePointLeft(1);
        transactionDAO.transferMoney(senderAccountBefore.getNumber(), receiverAccountBefore.getNumber(), amount);
        senderAccountBefore.setBalance(senderAccountBefore.getBalance().subtract(amount));
        receiverAccountBefore.setBalance(receiverAccountBefore.getBalance().add(amount));

        CreditAccount senderAccountAfter = creditAccountDAO.findByKey(senderAccountBefore.getId());
        assertEquals(senderAccountBefore, senderAccountAfter);

        CreditAccount receiverAccountAfter = creditAccountDAO.findByKey(receiverAccountBefore.getId());
        assertEquals(receiverAccountBefore, receiverAccountAfter);

        DBConnection.rollbackAndCloseConnection(connection);
    }

    @Test
    public void withdrawMoneyFromAccount() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.setConnection(connection);
        User user = TestEntities.getTestUser2();
        userDAO.create(user);

        CreditAccount account = TestEntities.getTestCreditAccount();
        account.setUserId(user.getId());


        CreditAccountDAO creditAccountDAO = new CreditAccountDAOImpl();
        creditAccountDAO.setConnection(connection);
        creditAccountDAO.create(account);


        TransactionDAOImpl transactionDAO = new TransactionDAOImpl();
        transactionDAO.setConnection(connection);

        BigDecimal amount = new BigDecimal(3567).movePointLeft(1);
        transactionDAO.withdrawMoneyFromAccount(account.getNumber(), amount);
        account.setBalance(account.getBalance().subtract(amount));


        CreditAccount accountAfter = creditAccountDAO.findByKey(account.getId());
        assertEquals(account, accountAfter);


        DBConnection.rollbackAndCloseConnection(connection);
    }
}