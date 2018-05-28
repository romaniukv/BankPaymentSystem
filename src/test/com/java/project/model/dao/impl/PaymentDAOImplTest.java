package com.java.project.model.dao.impl;

import com.java.project.model.domain.CreditAccount;
import com.java.project.services.DBConnection;
import com.java.project.utils.SetupTestDataBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class PaymentDAOImplTest {

    @Before
    public void setUp() {
        SetupTestDataBase.setup();
        DBConnection.setTestMode();
    }

    @After
    public void tearDown() {
        DBConnection.setApplicationMode();
    }

    @Test
    public void payBill() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        PaymentDAOImpl paymentDAO = new PaymentDAOImpl();
        paymentDAO.setConnection(connection);

        BigDecimal amount = new BigDecimal(100);
        assertTrue(paymentDAO.payBill("Sender", 3456789086453456L, "Receiver", 23545,
                amount, "Purpose"));

        CreditAccountDAOImpl creditAccountDAO = new CreditAccountDAOImpl();
        creditAccountDAO.setConnection(connection);
        CreditAccount accountAfter = creditAccountDAO.findByKey(1);

        boolean isEquals = new BigDecimal(400).compareTo(accountAfter.getBalance()) == 0;
        assertTrue(isEquals);


        DBConnection.rollbackAndCloseConnection(connection);
    }
}