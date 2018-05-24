package com.java.project.model.dao;

import com.java.project.model.dao.generic.GenericDAO;
import com.java.project.model.domain.DepositReplenishment;

import java.math.BigDecimal;
import java.util.List;

public interface DepositReplenishmentDAO extends GenericDAO<DepositReplenishment> {

    List<DepositReplenishment> selectAllByAccountNumber(long accountNumber);
    boolean replenishDeposit(long senderAccountNumber, long receiverAccountNumber, BigDecimal amount);

}
