package com.java.project.factory;

import com.java.project.model.dao.*;
import com.java.project.model.dao.impl.*;

public class DAOFactory {

    public static CreditAccountDAO getCreditAccountDAO() {
        return new CreditAccountDAOImpl();
    }

    public static DepositAccountDAO getDepositAccountDAO() {
        return new DepositAccountDAOImpl();
    }

    public static DepositReplenishmentDAO getDepositReplenishmentDAO() {
        return new DepositReplenishmentDAOImpl();
    }

    public static PaymentDAO getPaymentDAO() {
        return new PaymentDAOImpl();
    }

    public static TransactionDAO getTransacionDAO() {
        return new TransactionDAOImpl();
    }

    public static UserDAO getUserDAO() {
        return new UserDAOImpl();
    }

}
