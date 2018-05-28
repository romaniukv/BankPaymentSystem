package com.java.project.model.dao.impl;

import com.java.project.entities.TestEntities;
import com.java.project.model.domain.DepositAccount;
import com.java.project.services.DBConnection;
import com.java.project.utils.SetupTestDataBase;
import org.junit.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class DepositAccountDAOImplTest {

    @BeforeClass
    public static void setUp() {
        DBConnection.setTestMode();
        SetupTestDataBase.setup();
    }

    @AfterClass
    public static void tearDown() {
        DBConnection.setApplicationMode();
    }

    @Test
    public void selectByUserId() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        DepositAccountDAOImpl depositAccountDAO = new DepositAccountDAOImpl();
        depositAccountDAO.setConnection(connection);

        List<DepositAccount> depositAccounts = depositAccountDAO.selectByUserId(1);

        assertEquals(4, depositAccounts.size());

        DBConnection.rollbackAndCloseConnection(connection);
    }

    @Test
    public void selectAll() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        DepositAccountDAOImpl depositAccountDAO = new DepositAccountDAOImpl();
        depositAccountDAO.setConnection(connection);

        List<DepositAccount> depositAccounts = depositAccountDAO.selectAll();

        assertEquals(4, depositAccounts.size());

        DBConnection.rollbackAndCloseConnection(connection);
    }

    @Test
    public void create() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        DepositAccountDAOImpl depositAccountDAO = new DepositAccountDAOImpl();
        depositAccountDAO.setConnection(connection);

        DepositAccount depositAccount = TestEntities.getTestDepositAccount();

        depositAccountDAO.create(depositAccount);

        DepositAccount createdDepositAccount = depositAccountDAO.findByKey(depositAccount.getId());

        assertEquals(depositAccount.getNumber(), createdDepositAccount.getNumber());

        DBConnection.rollbackAndCloseConnection(connection);
    }

    @Test
    public void update() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        DepositAccountDAOImpl depositAccountDAO = new DepositAccountDAOImpl();
        depositAccountDAO.setConnection(connection);

        DepositAccount depositAccount = depositAccountDAO.findByKey(1);
        depositAccount.setAmount(new BigDecimal(1234));

        depositAccountDAO.update(depositAccount);

        DepositAccount updatedDepositAccount = depositAccountDAO.findByKey(1);

        boolean equals = depositAccount.getAmount().compareTo(updatedDepositAccount.getAmount()) == 0;
        assertTrue(equals);

        DBConnection.rollbackAndCloseConnection(connection);
    }

    @Test
    public void findByKey() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        DepositAccountDAOImpl depositAccountDAO = new DepositAccountDAOImpl();
        depositAccountDAO.setConnection(connection);

        DepositAccount depositAccount = depositAccountDAO.findByKey(3);

        assertEquals(3647586974630989L, depositAccount.getNumber());

        DBConnection.rollbackAndCloseConnection(connection);
    }

    @Test
    public void deleteByKey() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        DepositAccountDAOImpl depositAccountDAO = new DepositAccountDAOImpl();
        depositAccountDAO.setConnection(connection);

        DepositAccount depositAccount = depositAccountDAO.findByKey(3);

        assertNotNull(depositAccount);

        depositAccountDAO.deleteByKey(3);

        DepositAccount deletedDepositAccount = depositAccountDAO.findByKey(3);

        assertNull(deletedDepositAccount);

        DBConnection.rollbackAndCloseConnection(connection);
    }
}