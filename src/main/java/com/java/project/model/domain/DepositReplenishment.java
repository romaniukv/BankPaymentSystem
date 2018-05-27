package com.java.project.model.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Represents deposit account replenishment.
 */
public class DepositReplenishment extends Transaction {

    /**
     * Constructor which is used for creating new replenishment.
     */
    public DepositReplenishment(long senderAccountNumber, long receiverAccountNumber, BigDecimal amount, Date date) {
        super(senderAccountNumber, receiverAccountNumber, amount, date);
    }

    /**
     * Constructor which is used for reading existing replenishment from database.
     */
    public DepositReplenishment(int id, long senderAccountNumber, long receiverAccountNumber, BigDecimal amount, Date date) {
        super(id, senderAccountNumber, receiverAccountNumber, amount, date);
    }
}
