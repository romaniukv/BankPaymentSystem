package com.java.project.model.dao.impl;

import com.java.project.model.domain.User;
import com.java.project.services.DBConnection;
import entities.TestEntities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class UserDAOImplTest {

    @Before
    public void setUp() {
        DBConnection.setTestMode();
    }

    @After
    public void tearDown() {
        DBConnection.setApplicationMode();
    }


    @Test
    public void findUserByUsername() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.setConnection(connection);

        User expectedUser = TestEntities.getTestUser();
        User actualUser = userDAO.findUserByUsername(expectedUser.getUsername());

        assertEquals(expectedUser, actualUser);

        DBConnection.rollbackAndCloseConnection(connection);
    }

    @Test
    public void create() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.setConnection(connection);

        User expectedUser = TestEntities.getTestUser2();
        userDAO.create(expectedUser);

        User createdUser = userDAO.findByKey(expectedUser.getId());

        assertEquals(expectedUser, createdUser);

        DBConnection.rollbackAndCloseConnection(connection);
    }

    @Test
    public void update() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.setConnection(connection);

        User expectedUser = TestEntities.getTestUser2();
        userDAO.create(expectedUser);
        expectedUser.setEmail("newEmail");
        userDAO.update(expectedUser);

        User updatedUser = userDAO.findByKey(expectedUser.getId());

        assertEquals(expectedUser, updatedUser);

        DBConnection.rollbackAndCloseConnection(connection);
    }


    @Test
    public void deleteByKey() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.setConnection(connection);

        User user = TestEntities.getTestUser2();
        userDAO.create(user);
        userDAO.deleteByKey(user.getId());
        User deletedUser = userDAO.findByKey(user.getId());

        assertNull(deletedUser);

        DBConnection.rollbackAndCloseConnection(connection);
    }
}