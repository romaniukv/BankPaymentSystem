package com.java.project.model.dao.impl;

import com.java.project.factory.DAOFactory;
import com.java.project.model.dao.DepositReplenishmentDAO;
import com.java.project.model.dao.generic.impl.GenericDAOImpl;
import com.java.project.model.domain.DepositReplenishment;
import com.java.project.services.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class DepositReplenishmentDAOImpl extends GenericDAOImpl<DepositReplenishment> implements DepositReplenishmentDAO {

    private static final String SELECT_ALL_BY_ACCOUNT_NUMBER = "SELECT id,  sender_account_number, receiver_account_number, " +
            "amount, date FROM deposit_replenishments WHERE " +
            "receiver_account_number = ? ORDER BY date DESC LIMIT 10;";

    private static final String PUT_MONEY_TO_ACCOUNT = "UPDATE deposit_accounts SET balance = balance + ?, " +
            "amount = amount + ? WHERE number = ?";


    public DepositReplenishmentDAOImpl() {
        super("SELECT * FROM deposit_replenishments;",
                "INSERT INTO deposit_replenishments (sender_account_number, receiver_account_number, amount, date) VALUES(?, ?, ?, ?);",
                "UPDATE deposit_replenishments SET sender_account_number = ?, receiver_account_number = ?, amount = ?, date = ? WHERE id = ?;",
                "SELECT * FROM deposit_replenishments WHERE id = ?;",
                "DELETE FROM deposit_replenishments WHERE id = ?;",
                new String[][]{{"senderAccountNumber", "sender_account_number"},
                        {"receiverAccountNumber", "receiver_account_number"},
                        {"amount", "amount"},
                        {"date", "date"}},
                DepositReplenishment.class);
    }

    @Override
    public List<DepositReplenishment> selectAllByAccountNumber(long accountNumber) {
        List<DepositReplenishment> depositReplenishments = new ArrayList<>();
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_BY_ACCOUNT_NUMBER)) {
            ps.setLong(1, accountNumber);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                depositReplenishments.add(new DepositReplenishment(rs.getInt(1),rs.getLong(2),
                        rs.getLong(3), rs.getBigDecimal(4), rs.getTimestamp(5)));
            }
        } catch (SQLException e) {
        }
        return depositReplenishments;
    }

    @Override
    public boolean replenishDeposit(long senderAccountNumber, long receiverAccountNumber, BigDecimal amount) {
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement(PUT_MONEY_TO_ACCOUNT)) {
            ps.setBigDecimal(1, amount);
            ps.setBigDecimal(2, amount);
            ps.setLong(3,receiverAccountNumber);
            ps.execute();

            DepositReplenishment depositReplenishment = new DepositReplenishment(senderAccountNumber,
                    receiverAccountNumber, amount, new GregorianCalendar().getTime());

            DepositReplenishmentDAO replenishmentDAO = DAOFactory.getDepositReplenishmentDAO();
            replenishmentDAO.setConnection(connection);

            return replenishmentDAO.create(depositReplenishment);

        } catch (SQLException e) {
            return false;
        }
    }
}
