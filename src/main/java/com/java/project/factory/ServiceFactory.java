package com.java.project.factory;

import com.java.project.services.*;
import com.java.project.services.impl.*;

/**
 * Factory of services instances.
 */
public class ServiceFactory {

    /**
     * Gives credit account service instance.
     *
     * @return credit account service instance.
     */
    public static CreditAccountService getCreditAccountService() {
        return new CreditAccountServiceImpl();
    }

    /**
     * Gives deposit account service instance.
     *
     * @return deposit account service instance.
     */
    public static DepositAccountService getDepositAccountSrvice() {
        return new DepositAccountServiceImpl();
    }

    /**
     * Gives deposit replenishment service instance.
     *
     * @return deposit replenishment service instance.
     */
    public static DepositReplenishmentService getDepositReplenishmentService() {
        return new DepositReplenishmentServiceImpl();
    }

    /**
     * Gives payment service instance.
     *
     * @return payment service instance.
     */
    public static PaymentService getPaymentService() {
        return new PaymentServiceImpl();
    }

    /**
     * Gives transaction service instance.
     *
     * @return transaction service instance.
     */
    public static TransactionService getTransactionService() {
        return new TransactionServiceImpl();
    }

    /**
     * Gives user service instance.
     *
     * @return user service instance.
     */
    public static UserService getUserService() {
        return new UserServiceImpl();
    }

    /**
     * Gives bank configuration service instance.
     *
     * @return bank configuration service instance.
     */
    public static BankConfigService getBankConfigService() {
        return new BankConfigServiceImpl();
    }
}
