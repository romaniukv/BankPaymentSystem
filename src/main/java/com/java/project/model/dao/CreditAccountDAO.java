package com.java.project.model.dao;

import com.java.project.model.dao.generic.GenericDAO;
import com.java.project.model.domain.CreditAccount;

import java.util.List;

/**
 * DAO for credit account.
 */
public interface CreditAccountDAO extends GenericDAO<CreditAccount> {

    /**
     * Selects all new credit accounts.
     *
     * @return list of new credit accounts
     */
    List<CreditAccount> selectNewAccounts();

    /**
     * Selects credit account by user's id.
     *
     * @param userId user's id
     * @return user's credit account
     */
    CreditAccount selectByUserId(int userId);
}
