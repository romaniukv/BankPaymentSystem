package com.java.project.utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Stores constants for localization and changes language of web app.
 */
public class LocalizationUtils {

    private static ResourceBundle resourceBundle = ResourceBundle
            .getBundle("app_localization", Locale.getDefault());

    public static String CHANGES_SAVED;
    public static String CANT_MANAGE_ACCOUNT;
    public static String DEPOSIT_NOT_EXIST;
    public static String EDITING_ERROR;
    public static String USER_NOT_EXIST;
    public static String ALREADY_HAVE_CREDIT;
    public static String CANT_CREATE_CREDIT;
    public static String CREATE_CREDIT_SUCCESS;
    public static String CREATE_CREDIT_ERROR;
    public static String CREDIT_IS_CLOSED;
    public static String CANT_CREATE_DEPOSIT;
    public static String CREATE_DEPOSIT_ERROR;
    public static String DEPOSIT_IS_CLOSED;
    public static String DEPOSIT_NOT_FOUND;
    public static String CANT_REPLENISH;
    public static String CANT_PAY_BILL;
    public static String PAYMENT_FAILED;
    public static String PAYMENT_SUCCESS;
    public static String CANT_TRANSFER_MONEY;
    public static String TRANSACTION_SUCCESS;
    public static String TRANSACTION_FAILED;
    public static String WRONG_USERNAME_PASS;
    public static String REGISTER_ERROR;
    public static String OPENED;
    public static String CLOSED;
    public static String UNDER_CONSIDERATION;
    public static String NEW_DEPOSIT_ADDED;
    public static String CANT_ADD_DEPOSIT;

    static {
        updateLocale();
    }


    public static void changeLanguage(String language) {
        resourceBundle = ResourceBundle
                .getBundle("app_localization", new Locale(language));
        updateLocale();
    }


    private static void updateLocale() {
        CHANGES_SAVED = resourceBundle.getString("changesSaved");
        CANT_MANAGE_ACCOUNT = resourceBundle.getString("cantManageAccount");
        DEPOSIT_NOT_EXIST = resourceBundle.getString("depositNotExist");
        EDITING_ERROR = resourceBundle.getString("editingError");
        USER_NOT_EXIST = resourceBundle.getString("userNotExist");
        ALREADY_HAVE_CREDIT = resourceBundle.getString("alreadyHaveCredit");
        CANT_CREATE_CREDIT = resourceBundle.getString("cantCreateCredit");
        CREATE_CREDIT_SUCCESS = resourceBundle.getString("createCreditSuccess");
        CREATE_CREDIT_ERROR = resourceBundle.getString("createCreditError");
        CREDIT_IS_CLOSED = resourceBundle.getString("creditIsClosed");
        CANT_CREATE_DEPOSIT = resourceBundle.getString("cantCreateDeposit");
        CREATE_DEPOSIT_ERROR = resourceBundle.getString("createDepositError");
        DEPOSIT_IS_CLOSED = resourceBundle.getString("depositIsClosed");
        DEPOSIT_NOT_FOUND = resourceBundle.getString("depositNotFound");
        CANT_REPLENISH = resourceBundle.getString("cantReplenish");
        CANT_PAY_BILL = resourceBundle.getString("cantPayBill");
        PAYMENT_FAILED = resourceBundle.getString("paymentFailed");
        PAYMENT_SUCCESS = resourceBundle.getString("paymentSuccess");
        CANT_TRANSFER_MONEY = resourceBundle.getString("cantTransferMoney");
        TRANSACTION_SUCCESS = resourceBundle.getString("transactionSuccess");
        TRANSACTION_FAILED = resourceBundle.getString("transactionFailed");
        WRONG_USERNAME_PASS = resourceBundle.getString("wrongUsernamePass");
        REGISTER_ERROR = resourceBundle.getString("registerError");
        OPENED = resourceBundle.getString("opened");
        CLOSED = resourceBundle.getString("closed");
        UNDER_CONSIDERATION = resourceBundle.getString("underConsideration");
        NEW_DEPOSIT_ADDED = resourceBundle.getString("newDepositAdded");
        CANT_ADD_DEPOSIT = resourceBundle.getString("cantAddDeposit");
    }

}
