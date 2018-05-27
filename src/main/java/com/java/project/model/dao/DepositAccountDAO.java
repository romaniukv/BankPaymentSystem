package com.java.project.model.dao;

import com.java.project.model.dao.generic.GenericDAO;
import com.java.project.model.domain.DepositAccount;

import java.util.List;

/**
 * DAO for deposit account
 */
public interface DepositAccountDAO extends GenericDAO<DepositAccount> {

    /**
     * Selects user's deposit accounts bu user's id.
     *
     * @param userId user's id
     * @return list of user's deposit accounts
     */
    List<DepositAccount> selectByUserId(int userId);

}
