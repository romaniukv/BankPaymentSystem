package com.java.project.model.dao;

import com.java.project.model.dao.generic.GenericDAO;
import com.java.project.model.domain.Transaction;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

public interface TransactionDAO extends GenericDAO<Transaction> {

    List<Transaction> selectAllByAccountNumber(long accountNumber);
    boolean transferMoney(long fromAccount, long toAccount, BigDecimal amount);
    boolean withdrawMoneyFromAccount(Connection connection, long accountNumber, BigDecimal amount);

}
