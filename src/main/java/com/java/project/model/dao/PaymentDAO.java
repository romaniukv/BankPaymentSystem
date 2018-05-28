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

}
