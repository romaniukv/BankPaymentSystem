package com.java.project.model.dao;

import com.java.project.model.dao.generic.GenericDAO;
import com.java.project.model.domain.Transaction;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

/**
 * DAO for transaction
 */
public interface TransactionDAO extends GenericDAO<Transaction> {

    /**
     * Selects all transactions by credit account number.
     * @param accountNumber credit account number
     * @return list of all transactions
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

    /**
     * Withdraws money from account.
     * @param accountNumber account number
     * @param amount amount
     * @return @return <code>true</code> if the payment succeed;
     * <code>false</code> otherwise
     */
    boolean withdrawMoneyFromAccount(long accountNumber, BigDecimal amount);

}
