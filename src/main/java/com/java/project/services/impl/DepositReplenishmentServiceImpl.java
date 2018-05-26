package com.java.project.services.impl;

import com.java.project.factory.DAOFactory;
import com.java.project.model.dao.DepositReplenishmentDAO;
import com.java.project.model.dao.impl.DepositReplenishmentDAOImpl;
import com.java.project.model.domain.DepositReplenishment;
import com.java.project.services.DBConnection;
import com.java.project.services.DepositReplenishmentService;
import com.java.project.services.generic.impl.GenericServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DepositReplenishmentServiceImpl extends GenericServiceImpl<DepositReplenishment>
        implements DepositReplenishmentService {

    private static final Logger logger = LogManager.getLogger(DepositReplenishmentServiceImpl.class);

    private DepositReplenishmentDAO depositReplenishmentDAO;

    public DepositReplenishmentServiceImpl(DepositReplenishmentDAO depositReplenishmentDAO) {
        this.depositReplenishmentDAO = depositReplenishmentDAO;
        setDAOImpl(depositReplenishmentDAO);
    }

    public DepositReplenishmentServiceImpl(){
        this(DAOFactory.getDepositReplenishmentDAO());
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
            logger.error(e);
            DBConnection.rollbackAndCloseConnection(connection);
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
            logger.error(e);
            flag = false;
            DBConnection.rollbackAndCloseConnection(connection);
        }
        finally {
            DBConnection.closeConnection(connection);
        }
        return flag;
    }
}
