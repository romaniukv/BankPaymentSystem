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
     * {@link PaymentDAO#payBill(String, long, String, long, BigDecimal, String)}
     */
    boolean payBill(String senderName, long senderAccount, String receiverName, long receiverAccount, BigDecimal amount, String purpose);
}
