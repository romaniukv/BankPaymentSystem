package com.java.project.services;

import com.java.project.model.domain.Payment;
import com.java.project.services.generic.GenericService;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentService extends GenericService<Payment> {

    List<Payment> selectAllByAccountNumber(long accountNumber);
    boolean payBill(String senderName, long senderAccount, String receiverName, long receiverAccount, BigDecimal amount, String purpose);
}
