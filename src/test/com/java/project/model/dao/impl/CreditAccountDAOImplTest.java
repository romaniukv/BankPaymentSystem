package com.java.project.model.dao.impl;

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

public class CreditAccountDAOImplTest {

    @Before
    public void setUp() {
        DBConnection.setTestMode();
    }

    @After
    public void tearDown() {
        DBConnection.setApplicationMode();
    }

    @Test
    public void create() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        CreditAccountDAOImpl creditAccountDAO = new CreditAccountDAOImpl();
        creditAccountDAO.setConnection(connection);
        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.setConnection(connection);

        User user = TestEntities.getTestUser2();
        userDAO.create(user);

        CreditAccount expectedAccount = TestEntities.getTestCreditAccount();
        expectedAccount.setUserId(user.getId());

        creditAccountDAO.create(expectedAccount);

        CreditAccount createdAccount = creditAccountDAO.findByKey(expectedAccount.getId());

        assertEquals(expectedAccount, createdAccount);

        DBConnection.rollbackAndCloseConnection(connection);
    }

    @Test
    public void update() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        CreditAccountDAOImpl creditAccountDAO = new CreditAccountDAOImpl();
        creditAccountDAO.setConnection(connection);
        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.setConnection(connection);

        User user = TestEntities.getTestUser2();
        userDAO.create(user);

        CreditAccount expectedAccount = TestEntities.getTestCreditAccount();
        expectedAccount.setUserId(user.getId());

        creditAccountDAO.create(expectedAccount);
        expectedAccount.setIndebtedness(new BigDecimal(12023).movePointLeft(2));
        creditAccountDAO.update(expectedAccount);

        CreditAccount updatedAccount = creditAccountDAO.findByKey(expectedAccount.getId());

        assertEquals(expectedAccount, updatedAccount);

        DBConnection.rollbackAndCloseConnection(connection);
    }

    @Test
    public void deleteByKey() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        CreditAccountDAOImpl creditAccountDAO = new CreditAccountDAOImpl();
        creditAccountDAO.setConnection(connection);
        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.setConnection(connection);

        User user = TestEntities.getTestUser2();
        userDAO.create(user);

        CreditAccount expectedAccount = TestEntities.getTestCreditAccount();
        expectedAccount.setUserId(user.getId());

        creditAccountDAO.create(expectedAccount);

        CreditAccount createdAccount = creditAccountDAO.findByKey(expectedAccount.getId());

        assertEquals(expectedAccount, createdAccount);

        creditAccountDAO.deleteByKey(expectedAccount.getId());

        createdAccount = creditAccountDAO.findByKey(expectedAccount.getId());

        assertNull(createdAccount);

        DBConnection.rollbackAndCloseConnection(connection);
    }
}