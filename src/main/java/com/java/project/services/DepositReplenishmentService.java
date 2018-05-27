package com.java.project.services;

import com.java.project.model.dao.DepositReplenishmentDAO;
import com.java.project.model.domain.DepositReplenishment;
import com.java.project.services.generic.GenericService;

import java.math.BigDecimal;
import java.util.List;

/**
 * Service for deposit replenishment dao.
 */
public interface DepositReplenishmentService extends GenericService<DepositReplenishment> {

    /**
     * {@link DepositReplenishmentDAO#selectAllByAccountNumber(long)}
     */
    List<DepositReplenishment> selectAllByAccountNumber(long accountNumber);

    /**
     * {@link DepositReplenishmentDAO#replenishDeposit(long, long, BigDecimal)}
     */
    boolean replenishDeposit(long senderAccountNumber, long receiverAccountNumber, BigDecimal amount);
}
