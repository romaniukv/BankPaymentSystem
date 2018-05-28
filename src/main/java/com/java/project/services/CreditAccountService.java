package com.java.project.services;

import com.java.project.model.dao.CreditAccountDAO;
import com.java.project.model.domain.CreditAccount;
import com.java.project.services.generic.GenericService;

import java.math.BigDecimal;
import java.util.List;

/**
 * Service for credit account dao.
 */
public interface CreditAccountService extends GenericService<CreditAccount> {

    /**
     * {@link CreditAccountDAO#selectNewAccounts()}
     */
    List<CreditAccount> selectNewAccounts();

    /**
     * {@link CreditAccountDAO#selectByUserId(int)}
     */
    CreditAccount selectByUserId(int userId);

    /**
     * Withdraws money from account.
     * @param accountNumber account number
     * @param amount amount
     * @return @return <code>true</code> if the payment succeed;
     * <code>false</code> otherwise
     */
    boolean withdrawMoneyFromAccount(long accountNumber, BigDecimal amount);
}
