package com.java.project.entities;

import com.java.project.model.domain.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

public class TestEntities {

    public static User getTestUser() {
        return new User(Role.ADMIN, "romaniukv", "YnhKhE8/dy/YGgtqezqJGapWwOeoMHVO",
                "romaniukv255@gmail.com", "Vika", "Romaniuk");
    }

    public static User getTestUser2() {
        return new User(Role.USER, "romaniukv2", "password",
                "romaniukv255@gmail.com", "Vika", "Romaniuk");
    }

    public static CreditAccount getTestCreditAccount() {
        CreditAccount creditAccount = new CreditAccount(13456,1,new BigDecimal(500.0), new BigDecimal(2.5));
        creditAccount.calculateExpirationDate();
        creditAccount.setStatus(AccountStatus.UNDER_CONSIDERATION);
        return creditAccount;
    }

    public static CreditAccount getTestCreditAccount2() {
        CreditAccount creditAccount = new CreditAccount(33234,1,new BigDecimal(700.0), new BigDecimal(3.5));
        creditAccount.calculateExpirationDate();
        creditAccount.setStatus(AccountStatus.UNDER_CONSIDERATION);
        return creditAccount;
    }

    public static DepositAccount getTestDepositAccount() {
        return new DepositAccount(10, 3456876523450987L, new BigDecimal(500), 1, new BigDecimal(500),
                new BigDecimal(3.5), 12, AccountStatus.OPENED, GregorianCalendar.getInstance().getTime());
    }

    public static DepositReplenishment getTestDepositReplenishment() {
        return new DepositReplenishment(12932467823474L, 3647586974634567L, new BigDecimal(300),
                GregorianCalendar.getInstance().getTime());
    }
}
