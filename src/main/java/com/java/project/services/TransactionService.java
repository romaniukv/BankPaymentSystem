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
     * Transfers money.
     * @param fromAccount sender account number
     * @param toAccount receiver account number
     * @param amount amount
     * @return @return <code>true</code> if the transaction succeed;
     * <code>false</code> otherwise
     */
    boolean transferMoney(long fromAccount, long toAccount, BigDecimal amount);

}
