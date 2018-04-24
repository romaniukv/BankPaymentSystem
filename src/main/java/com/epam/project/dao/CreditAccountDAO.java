package com.epam.project.dao;

import com.epam.project.model.entities.CreditAccount;
import com.epam.project.model.entities.User;

public class CreditAccountDAO extends AbstractDAO<CreditAccount> {
    public CreditAccountDAO() {
        super("SELECT * FROM credit_accounts;",
                "INSERT INTO credit_accounts (balance, limit, indebtedness, accrued_interests, credit_rate)" +
                        " VALUES(?, ?, ?, ?, ?);",
                "UPDATE credit_accounts SET balance = ?, user_id = ?, expiration_date = ?, limit = ?," +
                        " indebtedness = ?, accrued_interests = ?, credit_rate = ? WHERE id = ?;",
                "SELECT * FROM credit_accounts WHERE id = ?;",
                "DELETE FROM credit_accounts WHERE id = ?;",
                new String[][]{{"balance", "balance"},
                        {"userId", "user_id"},
                        {"expirationDate", "expiration_date"},
                        {"limit", "limit"},
                        {"indebtedness", "indebtedness"},
                        {"accruedInterests", "accrued_interests"},
                        {"creditRate", "credit_rate"}},
                CreditAccount.class);
    }
}
