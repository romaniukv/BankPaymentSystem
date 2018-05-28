package com.java.project.services;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DBConnectionTest {

    @Test
    public void getConnection() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        assertNotNull(connection);
    }

    @Test
    public void closeConnection() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        assertNotNull(connection);
        DBConnection.closeConnection(connection);
        assertNotNull(connection);
    }
}