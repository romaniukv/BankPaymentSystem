package com.java.project.services.impl;

import com.java.project.model.dao.CreditAccountDAO;
import com.java.project.model.domain.CreditAccount;
import com.java.project.services.CreditAccountService;
import com.java.project.services.DBConnection;
import com.java.project.services.generic.impl.GenericServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CreditAccountServiceImpl extends GenericServiceImpl<CreditAccount> implements CreditAccountService {

    private CreditAccountDAO creditAccountDAO;

    public CreditAccountServiceImpl() {
        this.creditAccountDAO = new CreditAccountDAO();
        setAbstractDAO(creditAccountDAO);
    }

    @Override
    public List<CreditAccount> selectNewAccounts() {
        Connection connection = null;
        List<CreditAccount> creditAccounts = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            creditAccountDAO.setConnection(connection);
            creditAccounts = creditAccountDAO.selectNewAccounts();
            connection.commit();
        } catch (SQLException e) {
            DBConnection.rollbackConnection(connection);
        }
        finally {
            DBConnection.closeConnection(connection);
        }
        return creditAccounts;
    }

    @Override
    public CreditAccount selectByUserId(int userId) {
        Connection connection = null;
        CreditAccount creditAccount = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            creditAccountDAO.setConnection(connection);
            creditAccount = creditAccountDAO.selectByUserId(userId);
            connection.commit();
        } catch (SQLException e) {
            DBConnection.rollbackConnection(connection);
        }
        finally {
            DBConnection.closeConnection(connection);
        }
        return creditAccount;
    }
}
