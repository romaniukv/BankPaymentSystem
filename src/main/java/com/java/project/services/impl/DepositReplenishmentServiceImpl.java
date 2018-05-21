package com.java.project.services.impl;

import com.java.project.model.dao.DepositReplenishmentDAO;
import com.java.project.model.domain.DepositReplenishment;
import com.java.project.services.DBConnection;
import com.java.project.services.DepositReplenishmentService;
import com.java.project.services.generic.impl.GenericServiceImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DepositReplenishmentServiceImpl extends GenericServiceImpl<DepositReplenishment>
        implements DepositReplenishmentService {

    private DepositReplenishmentDAO depositReplenishmentDAO;

    public DepositReplenishmentServiceImpl() {
        this.depositReplenishmentDAO = new DepositReplenishmentDAO();
        setAbstractDAO(depositReplenishmentDAO);
    }

    @Override
    public List<DepositReplenishment> selectAllByAccountNumber(long accountNumber) {
        Connection connection = null;
        List<DepositReplenishment> depositReplenishments = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            depositReplenishmentDAO.setConnection(connection);
            depositReplenishments = depositReplenishmentDAO.selectAllByAccountNumber(accountNumber);
            connection.commit();
        } catch (SQLException e) {
            DBConnection.rollbackConnection(connection);
        }
        finally {
            DBConnection.closeConnection(connection);
        }
        return depositReplenishments;
    }

    @Override
    public boolean replenishDeposit(long senderAccountNumber, long receiverAccountNumber, BigDecimal amount) {
        Connection connection = null;
        boolean flag;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            depositReplenishmentDAO.setConnection(connection);
            flag = depositReplenishmentDAO.replenishDeposit(senderAccountNumber, receiverAccountNumber, amount);
            connection.commit();
        } catch (SQLException e) {
            flag = false;
            DBConnection.rollbackConnection(connection);
        }
        finally {
            DBConnection.closeConnection(connection);
        }
        return flag;
    }
}
