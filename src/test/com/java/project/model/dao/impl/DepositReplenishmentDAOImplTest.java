package com.java.project.model.dao.impl;

import com.java.project.entities.TestEntities;
import com.java.project.model.dao.DepositAccountDAO;
import com.java.project.model.domain.DepositAccount;
import com.java.project.model.domain.DepositReplenishment;
import com.java.project.services.DBConnection;
import com.java.project.utils.SetupTestDataBase;
import org.junit.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class DepositReplenishmentDAOImplTest {

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
    public void selectAllByAccountNumber() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        DepositReplenishmentDAOImpl depositReplenishmentDAO = new DepositReplenishmentDAOImpl();
        depositReplenishmentDAO.setConnection(connection);

        List<DepositReplenishment> depositReplenishments = depositReplenishmentDAO
                .selectAllByAccountNumber(3647586974634567L);

        assertEquals(3, depositReplenishments.size());

        DBConnection.rollbackAndCloseConnection(connection);
    }

    @Test
    public void replenishDeposit() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        DepositReplenishmentDAOImpl depositReplenishmentDAO = new DepositReplenishmentDAOImpl();
        depositReplenishmentDAO.setConnection(connection);

        depositReplenishmentDAO.replenishDeposit(1234, 3647586974636543L,
                new BigDecimal(500));

        DepositAccountDAO depositAccountDAO = new DepositAccountDAOImpl();
        depositAccountDAO.setConnection(connection);
        DepositAccount replenishedAccount = depositAccountDAO.findByKey(2);

        boolean isEquals =  new BigDecimal(1000).compareTo(replenishedAccount.getAmount()) == 0;

        assertTrue(isEquals);

        DBConnection.rollbackAndCloseConnection(connection);
    }

    @Test
    public void selectAll() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        DepositReplenishmentDAOImpl depositReplenishmentDAO = new DepositReplenishmentDAOImpl();
        depositReplenishmentDAO.setConnection(connection);

        List<DepositReplenishment> depositReplenishments = depositReplenishmentDAO.selectAll();

        assertEquals(3, depositReplenishments.size());

        DBConnection.rollbackAndCloseConnection(connection);
    }

    @Test
    public void create() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        DepositReplenishmentDAOImpl depositReplenishmentDAO = new DepositReplenishmentDAOImpl();
        depositReplenishmentDAO.setConnection(connection);

        DepositReplenishment depositReplenishment = TestEntities.getTestDepositReplenishment();

        depositReplenishmentDAO.create(depositReplenishment);

        DepositReplenishment createdReplenishment = depositReplenishmentDAO.findByKey(depositReplenishment.getId());

        assertEquals(depositReplenishment.getReceiverAccountNumber(), createdReplenishment.getReceiverAccountNumber());

        boolean isEquals = depositReplenishment.getAmount().compareTo(createdReplenishment.getAmount()) == 0;
        assertTrue(isEquals);

        DBConnection.rollbackAndCloseConnection(connection);
    }

    @Test
    public void update() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        DepositReplenishmentDAOImpl depositReplenishmentDAO = new DepositReplenishmentDAOImpl();
        depositReplenishmentDAO.setConnection(connection);

        DepositReplenishment depositReplenishment = depositReplenishmentDAO.findByKey(2);
        depositReplenishment.setAmount(new BigDecimal(250));
        depositReplenishmentDAO.update(depositReplenishment);

        DepositReplenishment updatedReplenishment = depositReplenishmentDAO.findByKey(2);

        boolean isEquals = depositReplenishment.getAmount().compareTo(updatedReplenishment.getAmount()) == 0;
        assertTrue(isEquals);

        DBConnection.rollbackAndCloseConnection(connection);
    }

    @Test
    public void findByKey() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        DepositReplenishmentDAOImpl depositReplenishmentDAO = new DepositReplenishmentDAOImpl();
        depositReplenishmentDAO.setConnection(connection);

        DepositReplenishment replenishment = depositReplenishmentDAO.findByKey(2);

        assertNotNull(replenishment);
        assertEquals(3647586974634567L, replenishment.getReceiverAccountNumber());

        DBConnection.rollbackAndCloseConnection(connection);
    }

    @Test
    public void deleteByKey() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        DepositReplenishmentDAOImpl depositReplenishmentDAO = new DepositReplenishmentDAOImpl();
        depositReplenishmentDAO.setConnection(connection);

        DepositReplenishment replenishment = depositReplenishmentDAO.findByKey(2);
        assertNotNull(replenishment);

        depositReplenishmentDAO.deleteByKey(2);

        DepositReplenishment deletedReplenishment = depositReplenishmentDAO.findByKey(2);

        assertNull(deletedReplenishment);

        DBConnection.rollbackAndCloseConnection(connection);
    }
}