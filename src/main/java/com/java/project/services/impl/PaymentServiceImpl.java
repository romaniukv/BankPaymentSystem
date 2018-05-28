package com.java.project.services.impl;

import com.java.project.factory.DAOFactory;
import com.java.project.model.dao.CreditAccountDAO;
import com.java.project.model.dao.PaymentDAO;
import com.java.project.model.dao.TransactionDAO;
import com.java.project.model.dao.impl.CreditAccountDAOImpl;
import com.java.project.model.domain.Payment;
import com.java.project.services.DBConnection;
import com.java.project.services.PaymentService;
import com.java.project.services.generic.impl.GenericServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Implementation of payment service.
 */
public class PaymentServiceImpl extends GenericServiceImpl<Payment> implements PaymentService {

    private static final Logger logger = LogManager.getLogger(PaymentServiceImpl.class);

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
            logger.error(e);
            DBConnection.rollbackAndCloseConnection(connection);
        }
        finally {
            DBConnection.closeConnection(connection);
        }
        return payments;
    }


    @Override
    public boolean payBill(String senderName, long senderAccount, String receiverName, long receiverAccount,
                           BigDecimal amount, String purpose) {
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            TransactionDAO transactionDAO = DAOFactory.getTransacionDAO();
            transactionDAO.setConnection(connection);

            boolean flag = new CreditAccountServiceImpl().withdrawMoneyFromAccount(senderAccount, amount);
            if (!flag) {
                return false;
            }

            Payment payment = new Payment(senderName, senderAccount, receiverName, receiverAccount, amount, purpose,
                    new GregorianCalendar().getTime());

            return create(payment);
        } catch (SQLException e) {
            logger.error(e);
            return false;
        }

    }
}
