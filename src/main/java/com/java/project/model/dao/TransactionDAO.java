package com.java.project.model.dao;

import com.java.project.model.entities.Transaction;

public class TransactionDAO extends AbstractDAO<Transaction> {
    public TransactionDAO() {
        super("SELECT * FROM transactions LIMIT 10;",
                "INSERT INTO transactions (from_number, to_number, amount, date) VALUES(?, ?, ?, ?);",
                "UPDATE transactions SET from_number = ?, to_number = ?, amount = ?, date = ? WHERE id = ?;",
                "SELECT * FROM transactions WHERE id = ?;",
                "DELETE FROM transactions WHERE id = ?;",
                new String[][]{{"fromNumber", "from_number"},
                        {"toNumber", "to_number"},
                        {"amount", "amount"},
                        {"date", "date"}},
                Transaction.class);
    }
}
