package com.java.project.services.impl;

import com.java.project.model.dao.UserDAO;
import com.java.project.model.domain.User;
import com.java.project.services.DBConnection;
import com.java.project.services.UserService;
import com.java.project.services.generic.impl.GenericServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceImpl extends GenericServiceImpl<User> implements UserService {

    private UserDAO userDAO;

    public UserServiceImpl() {
        this.userDAO = new UserDAO();
        setAbstractDAO(userDAO);
    }

    @Override
    public User findUserByUsername(String username) {
        Connection connection = null;
        User user = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            userDAO.setConnection(connection);
            user = userDAO.findUserByUsername(username);
            connection.commit();
        } catch (SQLException e) {
            DBConnection.rollbackAndCloseConnection(connection);
        }
        finally {
            DBConnection.closeConnection(connection);
        }
        return user;
    }
}
