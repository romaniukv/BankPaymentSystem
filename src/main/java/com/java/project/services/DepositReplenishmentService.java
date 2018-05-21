package com.java.project.services;

import com.java.project.model.domain.DepositReplenishment;
import com.java.project.services.generic.GenericService;

import java.math.BigDecimal;
import java.util.List;

public interface DepositReplenishmentService extends GenericService<DepositReplenishment> {

    List<DepositReplenishment> selectAllByAccountNumber(long accountNumber);
    boolean replenishDeposit(long senderAccountNumber, long receiverAccountNumber, BigDecimal amount);
}
