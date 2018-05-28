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


}
