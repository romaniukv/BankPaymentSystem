package com.java.project.services;

import com.java.project.model.domain.DepositAccount;
import com.java.project.services.generic.GenericService;

import java.util.List;

public interface DepositAccountService extends GenericService<DepositAccount> {

    List<DepositAccount> selectByUserId(int userId);
}
