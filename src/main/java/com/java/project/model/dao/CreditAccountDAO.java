package com.java.project.model.dao;

import com.java.project.model.dao.generic.GenericDAO;
import com.java.project.model.domain.CreditAccount;

import java.util.List;

public interface CreditAccountDAO extends GenericDAO<CreditAccount> {

    List<CreditAccount> selectNewAccounts();
    CreditAccount selectByUserId(int userId);
}
