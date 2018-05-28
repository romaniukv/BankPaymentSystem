package com.java.project.services.impl;

import com.java.project.model.dao.impl.CreditAccountDAOImpl;
import com.java.project.model.dao.impl.PaymentDAOImpl;
import com.java.project.model.domain.CreditAccount;
import com.java.project.services.DBConnection;
import com.java.project.utils.SetupTestDataBase;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class PaymentServiceImplTest {

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
    public void payBill() throws SQLException {
        PaymentServiceImpl paymentService = new PaymentServiceImpl();

        BigDecimal amount = new BigDecimal(100);
        assertTrue(paymentService.payBill("Sender", 3456789086453456L, "Receiver", 23545,
                amount, "Purpose"));

        Connection connection = DBConnection.getInstance().getConnection();
        CreditAccountDAOImpl creditAccountDAO = new CreditAccountDAOImpl();
        creditAccountDAO.setConnection(connection);
        CreditAccount accountAfter = creditAccountDAO.findByKey(1);

        boolean isEquals = new BigDecimal(400).compareTo(accountAfter.getBalance()) == 0;
        assertTrue(isEquals);

        DBConnection.rollbackAndCloseConnection(connection);
    }
}