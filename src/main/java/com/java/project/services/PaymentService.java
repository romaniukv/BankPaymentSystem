package com.java.project.services;

import com.java.project.model.dao.PaymentDAO;
import com.java.project.model.domain.Payment;
import com.java.project.services.generic.GenericService;

import java.math.BigDecimal;
import java.util.List;

/**
 * Service for payment dao.
 */
public interface PaymentService extends GenericService<Payment> {

    /**
     * {@link PaymentDAO#selectAllByAccountNumber(long)}
     */
    List<Payment> selectAllByAccountNumber(long accountNumber);

    /**
     * Pays bill.
     * @param senderName sender name
     * @param senderAccount sender account number
     * @param receiverName receiver name
     * @param receiverAccount receiver account number
     * @param amount amount
     * @param purpose purpose of payment
     * @return @return <code>true</code> if the payment succeed;
     * <code>false</code> otherwise
     */
    boolean payBill(String senderName, long senderAccount, String receiverName, long receiverAccount, BigDecimal amount, String purpose);
}
