package com.java.project.services.impl;

import com.java.project.model.dao.CreditAccountDAO;
import com.java.project.model.dao.impl.CreditAccountDAOImpl;
import com.java.project.model.dao.impl.TransactionDAOImpl;
import com.java.project.model.domain.CreditAccount;
import com.java.project.services.DBConnection;
import com.java.project.utils.SetupTestDataBase;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class TransactionServiceImplTest {

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
    public void transferMoney() throws SQLException {
        TransactionServiceImpl transactionService = new TransactionServiceImpl();

        BigDecimal amount = new BigDecimal(100);
        transactionService.transferMoney(3456789086453456L, 3456789234453456L, amount);

        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        CreditAccountDAO creditAccountDAO = new CreditAccountDAOImpl();
        creditAccountDAO.setConnection(connection);
        
        CreditAccount senderAccount = creditAccountDAO.findByKey(1);
        boolean isEquals = new BigDecimal(400).compareTo(senderAccount.getBalance()) == 0;
        assertTrue(isEquals);

        CreditAccount receiverAccount = creditAccountDAO.findByKey(5);
        isEquals = new BigDecimal(600).compareTo(receiverAccount.getBalance()) == 0;
        assertTrue(isEquals);

        DBConnection.rollbackAndCloseConnection(connection);
    }
}