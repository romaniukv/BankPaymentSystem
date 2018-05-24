package com.java.project.model.dao;

import com.java.project.model.dao.generic.GenericDAO;
import com.java.project.model.domain.DepositAccount;

import java.util.List;

public interface DepositAccountDAO extends GenericDAO<DepositAccount> {

    List<DepositAccount> selectByUserId(int userId);

}
