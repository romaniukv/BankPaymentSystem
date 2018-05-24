package com.java.project.services;

import com.java.project.model.domain.DepositAccount;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface BankConfigService {

    Map<BigDecimal, BigDecimal> selectCreditLimits();

    long getNewAccountNumber();

    List<DepositAccount> selectAvailableDepositAccountsFromCatalog();

    DepositAccount findDepositInCatalog(int id);

    boolean addDepositToCatalog(DepositAccount deposit);

    void removeDepositFromCatalog(int id);

    boolean updateDepositInCatalog(String name, int term, BigDecimal rate, int id);

}
