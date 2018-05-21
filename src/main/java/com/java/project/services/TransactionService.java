package com.java.project.services;

import com.java.project.model.domain.Transaction;
import com.java.project.services.generic.GenericService;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService extends GenericService<Transaction> {

    List<Transaction> selectAllByAccountNumber(long accountNumber);
    boolean transferMoney(long fromAccount, long toAccount, BigDecimal amount);
    boolean withdrawMoneyFromAccount(long accountNumber, BigDecimal amount);
}
