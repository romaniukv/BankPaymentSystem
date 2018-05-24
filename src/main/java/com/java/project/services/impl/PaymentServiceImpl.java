package com.java.project.services.impl;

import com.java.project.factory.DAOFactory;
import com.java.project.model.dao.PaymentDAO;
import com.java.project.model.dao.impl.PaymentDAOImpl;
import com.java.project.model.domain.Payment;
import com.java.project.services.DBConnection;
import com.java.project.services.PaymentService;
import com.java.project.services.generic.impl.GenericServiceImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class PaymentServiceImpl extends GenericServiceImpl<Payment> implements PaymentService {

    private PaymentDAO paymentDAO;

    public PaymentServiceImpl(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
        setDAOImpl(paymentDAO);
    }

    public PaymentServiceImpl() {
        this(DAOFactory.getPaymentDAO());
    }

    @Override
    public List<Payment> selectAllByAccountNumber(long accountNumber) {
        Connection connection = null;
        List<Payment> payments = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            paymentDAO.setConnection(connection);
            payments = paymentDAO.selectAllByAccountNumber(accountNumber);
            connection.commit();
        } catch (SQLException e) {
            DBConnection.rollbackAndCloseConnection(connection);
        }
        finally {
            DBConnection.closeConnection(connection);
        }
        return payments;
    }


    @Override
    public boolean payBill(String senderName, long senderAccount, String receiverName, long receiverAccount, BigDecimal amount, String purpose) {
        Connection connection = null;
        boolean flag;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            paymentDAO.setConnection(connection);
            flag = paymentDAO.payBill(senderName, senderAccount, receiverName, receiverAccount, amount, purpose);
            if (flag)
                connection.commit();
            else
                connection.rollback();
        } catch (SQLException e) {
            flag = false;
            DBConnection.rollbackAndCloseConnection(connection);
        }
        finally {
            DBConnection.closeConnection(connection);
        }
        return flag;
    }
}
