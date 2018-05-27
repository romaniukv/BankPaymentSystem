package com.java.project.services;

import com.java.project.model.domain.DepositAccount;
import com.java.project.services.generic.GenericService;
import com.java.project.model.dao.DepositAccountDAO;

import java.util.List;

/**
 * Service for deposit account dao.
 */
public interface DepositAccountService extends GenericService<DepositAccount> {

    /**
     * {@link DepositAccountDAO#selectByUserId(int)}
     */
    List<DepositAccount> selectByUserId(int userId);
}
