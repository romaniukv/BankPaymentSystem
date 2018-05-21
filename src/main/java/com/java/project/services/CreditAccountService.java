package com.java.project.services;

import com.java.project.model.domain.CreditAccount;
import com.java.project.services.generic.GenericService;

import java.util.List;

public interface CreditAccountService extends GenericService<CreditAccount> {

    List<CreditAccount> selectNewAccounts();
    CreditAccount selectByUserId(int userId);
}
