package com.java.project.model.dao.impl;

import com.java.project.model.dao.CreditAccountDAO;
import com.java.project.model.domain.CreditAccount;
import com.java.project.model.domain.User;
import com.java.project.services.DBConnection;
import com.java.project.entities.TestEntities;
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


        CreditAccountDAO creditAccountDAO = new CreditAccountDAOImpl();
        creditAccountDAO.setConnection(connection);

        TransactionDAOImpl transactionDAO = new TransactionDAOImpl();
        transactionDAO.setConnection(connection);

        BigDecimal amount = new BigDecimal(100);
        transactionDAO.transferMoney(3456789086453456L, 3456789234453456L, amount);

        CreditAccount senderAccount = creditAccountDAO.findByKey(1);
        boolean isEquals = new BigDecimal(400).compareTo(senderAccount.getBalance()) == 0;
        assertTrue(isEquals);

        CreditAccount receiverAccount = creditAccountDAO.findByKey(5);
        isEquals = new BigDecimal(600).compareTo(receiverAccount.getBalance()) == 0;
        assertTrue(isEquals);

        DBConnection.rollbackAndCloseConnection(connection);
    }

}