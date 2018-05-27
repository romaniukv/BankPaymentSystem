package com.java.project.factory;

import com.java.project.model.dao.*;
import com.java.project.model.dao.impl.*;

/**
 * Factory of dao instances.
 * Used in service layer.
 */
public class DAOFactory {

    /**
     * Gives credit account dao instance.
     *
     * @return credit account dao instance.
     */
    public static CreditAccountDAO getCreditAccountDAO() {
        return new CreditAccountDAOImpl();
    }

    /**
     * Gives deposit account dao instance.
     *
     * @return deposit account dao instance.
     */
    public static DepositAccountDAO getDepositAccountDAO() {
        return new DepositAccountDAOImpl();
    }

    /**
     * Gives deposit replenishment dao instance.
     *
     * @return deposit replenishment dao instance.
     */
    public static DepositReplenishmentDAO getDepositReplenishmentDAO() {
        return new DepositReplenishmentDAOImpl();
    }

    /**
     * Gives payment dao instance.
     *
     * @return payment dao instance.
     */
    public static PaymentDAO getPaymentDAO() {
        return new PaymentDAOImpl();
    }

    /**
     * Gives transaction dao instance.
     *
     * @return transaction dao instance.
     */
    public static TransactionDAO getTransacionDAO() {
        return new TransactionDAOImpl();
    }

    /**
     * Gives user dao instance.
     *
     * @return user dao instance.
     */
    public static UserDAO getUserDAO() {
        return new UserDAOImpl();
    }

}
