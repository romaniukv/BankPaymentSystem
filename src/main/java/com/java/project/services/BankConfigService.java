package com.java.project.services;

import com.java.project.model.domain.DepositAccount;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Service for bank configurations.
 */
public interface BankConfigService {

    /**
     * Selects all available credit limits.
     * @return list of all available credit limits
     */
    Map<BigDecimal, BigDecimal> selectCreditLimits();

    /**
     * @return number for new account
     */
    long getNewAccountNumber();

    /**
     * Selects all available deposits from catalog.
     * @return list of all available deposits from catalog.
     */
    List<DepositAccount> selectAvailableDepositAccountsFromCatalog();

    /**
     * Searches for deposit in catalog by id.
     * @param id deposit id
     * @return deposit with such id
     */
    DepositAccount findDepositInCatalog(int id);

    /**
     * Adds new deposit to catalog
     * @param deposit deposit to add
     * @return <code>true</code> if deposit added successfully
     * <code>false</code> otherwise
     */
    boolean addDepositToCatalog(DepositAccount deposit);

    /**
     * Removes deposit from catalog by id.
     * @param id id of deposit
     */
    void removeDepositFromCatalog(int id);

    /**
     * Updates deposit in catalog.
     * @param name name of deposit
     * @param term term of deposit
     * @param rate rate of deposit
     * @param id id of deposit
     * @return <code>true</code> if deposit updated successfully
     * <code>false</code> otherwise
     */
    boolean updateDepositInCatalog(String name, int term, BigDecimal rate, int id);

}
