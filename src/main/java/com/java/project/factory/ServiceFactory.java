package com.java.project.factory;

import com.java.project.services.*;
import com.java.project.services.impl.*;

public class ServiceFactory {

    public static CreditAccountService getCreditAccountService() {
        return new CreditAccountServiceImpl();
    }

    public static DepositAccountService getDepositAccountSrvice() {
        return new DepositAccountServiceImpl();
    }

    public static DepositReplenishmentService getDepositReplenishmentService() {
        return new DepositReplenishmentServiceImpl();
    }

    public static PaymentService getPaymentService() {
        return new PaymentServiceImpl();
    }

    public static TransactionService getTransactionService() {
        return new TransactionServiceImpl();
    }

    public static UserService getUserService() {
        return new UserServiceImpl();
    }

    public static BankConfigService getBankConfigService() {
        return new BankConfigServiceImpl();
    }
}
