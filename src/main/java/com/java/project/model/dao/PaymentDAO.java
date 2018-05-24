package com.java.project.model.dao;

import com.java.project.model.dao.generic.GenericDAO;
import com.java.project.model.domain.Payment;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentDAO extends GenericDAO<Payment> {

    List<Payment> selectAllByAccountNumber(long accountNumber);
    boolean payBill(String senderName, long senderAccount, String receiverName, long receiverAccount, BigDecimal amount, String purpose);

}
