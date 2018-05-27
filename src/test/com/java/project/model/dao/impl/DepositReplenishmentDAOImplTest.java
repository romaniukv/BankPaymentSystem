package com.java.project.model.dao.impl;

import com.java.project.model.domain.DepositAccount;
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

public class DepositReplenishmentDAOImplTest {

    @Before
    public void setUp() {
        DBConnection.setTestMode();
    }

    @After
    public void tearDown() {
        DBConnection.setApplicationMode();
    }

    @Test
    public void replenishDeposit() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.setConnection(connection);
        User user = TestEntities.getTestUser2();
        userDAO.create(user);

        DepositAccount depositAccount = TestEntities.getTestDepositAccount();
        depositAccount.setUserId(user.getId());
        DepositAccountDAOImpl depositAccountDAO = new DepositAccountDAOImpl();
        depositAccountDAO.setConnection(connection);
        depositAccountDAO.create(depositAccount);

        DepositReplenishmentDAOImpl replenishmentDAO = new DepositReplenishmentDAOImpl();
        replenishmentDAO.setConnection(connection);
        BigDecimal amount = new BigDecimal(20);
        replenishmentDAO.replenishDeposit(3454, depositAccount.getNumber(), amount);

        depositAccount.setAmount(depositAccount.getAmount().add(amount));

        DepositAccount replenishedAccount = depositAccountDAO.findByKey(depositAccount.getId());

        assertEquals(depositAccount, replenishedAccount);

        DBConnection.rollbackAndCloseConnection(connection);
    }
}