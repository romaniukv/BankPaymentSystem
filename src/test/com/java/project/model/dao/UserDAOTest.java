package com.java.project.model.dao;

import com.java.project.model.domain.User;
import com.java.project.services.DBConnection;
import entities.TestEntities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDAOTest {

    @Before
    public void setUp() throws Exception {
        DBConnection.setTestMode();
    }

    @After
    public void tearDown() throws Exception {
        DBConnection.setApplicationMode();
    }

    @Test
    public void findUserByUsername() {
        User expectedUser = TestEntities.getTestUser();
        UserDAO userDAO = new UserDAO();
        User actualUser = userDAO.findUserByUsername("romaniukv");
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void create() {
        User expectedUser = TestEntities.getTestUser2();
        UserDAO userDAO = new UserDAO();
        userDAO.create(expectedUser);
        User createdUser = userDAO.findByKey(expectedUser.getId());
        assertEquals(expectedUser, createdUser);
    }

    @Test
    public void update() {
        UserDAO userDAO = new UserDAO();
        User expectedUser = TestEntities.getTestUser2();
        userDAO.create(expectedUser);
        expectedUser.setEmail("newEmail");
        userDAO.update(expectedUser);
        User updatedUser = userDAO.findByKey(expectedUser.getId());
        assertEquals(expectedUser, updatedUser);
    }

    @Test
    public void findByKey() {
    }

    @Test
    public void deleteByKey() {
    }
}