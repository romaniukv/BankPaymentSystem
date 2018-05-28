package com.java.project.model.dao.impl;

import com.java.project.model.domain.CreditAccount;
import com.java.project.model.domain.User;
import com.java.project.services.DBConnection;
import com.java.project.entities.TestEntities;
import com.java.project.utils.SetupTestDataBase;
import org.junit.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class CreditAccountDAOImplTest {

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
    public void create() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        CreditAccountDAOImpl creditAccountDAO = new CreditAccountDAOImpl();
        creditAccountDAO.setConnection(connection);

        CreditAccount expectedAccount = TestEntities.getTestCreditAccount();
        expectedAccount.setUserId(1);

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

        CreditAccount expectedAccount = creditAccountDAO.findByKey(1);
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

        CreditAccount account = creditAccountDAO.findByKey(1);

        assertNotNull(account);

        creditAccountDAO.deleteByKey(account.getId());

        CreditAccount deletedAccount = creditAccountDAO.findByKey(1);

        assertNull(deletedAccount);

        DBConnection.rollbackAndCloseConnection(connection);
    }

    @Test
    public void findByKey() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        CreditAccountDAOImpl creditAccountDAO = new CreditAccountDAOImpl();
        creditAccountDAO.setConnection(connection);

        CreditAccount account = creditAccountDAO.findByKey(1);

        assertNotNull(account);

        assertEquals(3456789086453456L, account.getNumber());

        DBConnection.rollbackAndCloseConnection(connection);
    }

    @Test
    public void selectAll() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        CreditAccountDAOImpl creditAccountDAO = new CreditAccountDAOImpl();
        creditAccountDAO.setConnection(connection);

        List<CreditAccount> newCreditAccounts = creditAccountDAO.selectAll();

        assertEquals(5, newCreditAccounts.size());

        DBConnection.rollbackAndCloseConnection(connection);

    }

    @Test
    public void selectNewAccounts() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        CreditAccountDAOImpl creditAccountDAO = new CreditAccountDAOImpl();
        creditAccountDAO.setConnection(connection);

        List<CreditAccount> newCreditAccounts = creditAccountDAO.selectNewAccounts();

        assertEquals(3, newCreditAccounts.size());

        DBConnection.rollbackAndCloseConnection(connection);

    }

    @Test
    public void selectByUserId() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        CreditAccountDAOImpl creditAccountDAO = new CreditAccountDAOImpl();
        creditAccountDAO.setConnection(connection);

        CreditAccount creditAccount = creditAccountDAO.selectByUserId(2);

        assertEquals(3456789234453456L, creditAccount.getNumber());

        DBConnection.rollbackAndCloseConnection(connection);
    }
}