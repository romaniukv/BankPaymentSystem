package com.java.project.model.domain;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Credit account.
 */
public class CreditAccount extends Account {

    /**
     * Limit of credit account
     */
    private BigDecimal limit;

    /**
     * Indebtedness of credit account
     */
    private BigDecimal indebtedness = new BigDecimal(0.0);

    /**
     * Accrued interests of credit account
     */
    private BigDecimal accruedInterest = new BigDecimal(0.0);

    /**
     * Rate of credit account
     */
    private BigDecimal creditRate;

    public CreditAccount() {

    }

    /**
     * Constructor which is used for creating new credit account.
     */
    public CreditAccount(long number, int userId,  BigDecimal limit, BigDecimal creditRate) {
        super(number, userId, limit);
        this.limit = limit;
        this.creditRate = creditRate;
        this.indebtedness = limit;
        setBalance(limit);
    }

    public CreditAccount(long number, int id, int userId,  BigDecimal limit, BigDecimal creditRate) {
        super(number, userId, limit);
        this.limit = limit;
        this.creditRate = creditRate;
        setId(id);
        setBalance(limit);
    }

    /**
     * Constructor which is used for reading existing credit account from database.
     */
    public CreditAccount(int id, long number, BigDecimal balance, int userId, BigDecimal limit,
                         BigDecimal indebtedness, BigDecimal accruedInterest, BigDecimal creditRate,
                         AccountStatus status, Date expirationDate) {
        super(id, number, userId, balance, status, expirationDate);
        this.limit = limit;
        this.indebtedness = indebtedness;
        this.accruedInterest = accruedInterest;
        this.creditRate = creditRate;
    }

    @Override
    public void calculateExpirationDate() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.add(Calendar.YEAR, 2);
        setExpirationDate(gregorianCalendar.getTime());
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public BigDecimal getIndebtedness() {
        return indebtedness;
    }

    public BigDecimal getAccruedInterest() {
        return accruedInterest;
    }

    public BigDecimal getCreditRate() {
        return creditRate;
    }

    public void setIndebtedness(BigDecimal indebtedness) {
        this.indebtedness = indebtedness;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditAccount that = (CreditAccount) o;

        return creditRate.compareTo(that.creditRate) == 0
                && limit.compareTo(that.limit) == 0
                && indebtedness.compareTo(that.indebtedness) == 0
                && accruedInterest.compareTo(that.accruedInterest) == 0;
    }

    @Override
    public int hashCode() {
        int result = limit.hashCode();
        result = 31 * result + indebtedness.hashCode();
        result = 31 * result + accruedInterest.hashCode();
        result = 31 * result + creditRate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CreditAccount{" +
                "limit=" + limit +
                ", indebtedness=" + indebtedness +
                ", accruedInterest=" + accruedInterest +
                ", creditRate=" + creditRate +
                '}';
    }
}
