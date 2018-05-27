package com.java.project.model.dao;

import com.java.project.model.dao.generic.GenericDAO;
import com.java.project.model.domain.Payment;

import java.math.BigDecimal;
import java.util.List;

/**
 * DAO for payment.
 */
public interface PaymentDAO extends GenericDAO<Payment> {

    /**
     * Selects all payments by credit account number.
     * @param accountNumber credit account number
     * @return list of all payments
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
