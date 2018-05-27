package com.java.project.model.dao;

import com.java.project.model.dao.generic.GenericDAO;
import com.java.project.model.domain.DepositReplenishment;

import java.math.BigDecimal;
import java.util.List;

/**
 * DAO for deposit replenishment.
 */
public interface DepositReplenishmentDAO extends GenericDAO<DepositReplenishment> {

    /**
     * Selects all replenishments by account number
     *
     * @param accountNumber account number
     * @return list of all replenishments
     */
    List<DepositReplenishment> selectAllByAccountNumber(long accountNumber);

    /**
     * Replenishes deposit.
     *
     * @param senderAccountNumber   sender account number
     * @param receiverAccountNumber number of deposit to replenish
     * @param amount                amount of replenishment
     * @return @return <code>true</code> if the deposit is successfully replenished;
     * <code>false</code> otherwise
     */
    boolean replenishDeposit(long senderAccountNumber, long receiverAccountNumber, BigDecimal amount);
}
