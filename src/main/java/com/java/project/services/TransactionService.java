package com.java.project.services;

import com.java.project.model.dao.TransactionDAO;
import com.java.project.model.domain.Transaction;
import com.java.project.services.generic.GenericService;

import java.math.BigDecimal;
import java.util.List;

/**
 * Service for transaction dao.
 */
public interface TransactionService extends GenericService<Transaction> {

    /**
     * {@link TransactionDAO#selectAllByAccountNumber(long)}
     */
    List<Transaction> selectAllByAccountNumber(long accountNumber);

    /**
     * {@link TransactionDAO#transferMoney(long, long, BigDecimal)}
     */
    boolean transferMoney(long fromAccount, long toAccount, BigDecimal amount);

    /**
     * {@link TransactionDAO#withdrawMoneyFromAccount(long, BigDecimal)}
     */
    boolean withdrawMoneyFromAccount(long accountNumber, BigDecimal amount);
}
