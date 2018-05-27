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

public class PaymentDAOImplTest {

    @Before
    public void setUp() {
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

        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.setConnection(connection);
        User user = TestEntities.getTestUser2();
        userDAO.create(user);

        CreditAccount account = TestEntities.getTestCreditAccount();
        account.setUserId(user.getId());


        CreditAccountDAO creditAccountDAO = new CreditAccountDAOImpl();
        creditAccountDAO.setConnection(connection);
        creditAccountDAO.create(account);


        PaymentDAOImpl paymentDAO = new PaymentDAOImpl();
        paymentDAO.setConnection(connection);

        BigDecimal amount = new BigDecimal(3567).movePointLeft(1);
        paymentDAO.payBill("Sender", 1573, user.getLastName(),account.getNumber(), amount, "purpose");
        account.setBalance(account.getBalance().subtract(amount));


        CreditAccount accountAfter = creditAccountDAO.findByKey(account.getId());
        assertEquals(account, accountAfter);


        DBConnection.rollbackAndCloseConnection(connection);
    }
}