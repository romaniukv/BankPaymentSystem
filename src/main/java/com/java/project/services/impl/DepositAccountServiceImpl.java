package com.java.project.services.impl;

import com.java.project.model.dao.DepositAccountDAO;
import com.java.project.model.domain.DepositAccount;

import com.java.project.services.DBConnection;
import com.java.project.services.DepositAccountService;
import com.java.project.services.generic.impl.GenericServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DepositAccountServiceImpl extends GenericServiceImpl<DepositAccount> implements DepositAccountService {

    private DepositAccountDAO depositAccountDAO;

    public DepositAccountServiceImpl() {
        this.depositAccountDAO = new DepositAccountDAO();
        setAbstractDAO(depositAccountDAO);
    }

    @Override
    public List<DepositAccount> selectByUserId(int userId) {
        Connection connection = null;
        List<DepositAccount> depositAccounts = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            depositAccountDAO.setConnection(connection);
            depositAccounts = depositAccountDAO.selectByUserId(userId);
            connection.commit();
        } catch (SQLException e) {
            DBConnection.rollbackAndCloseConnection(connection);
        }
        finally {
            DBConnection.closeConnection(connection);
        }
        return depositAccounts;
    }
}
