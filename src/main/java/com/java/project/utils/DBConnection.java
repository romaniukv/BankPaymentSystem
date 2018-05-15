package com.java.project.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnection {
    private static ComboPooledDataSource dataSource;
    private static final String DRIVER_NAME;
    private static final String URL;
    private static final String USER_NAME;
    private static final String PASSWORD;

    static {
        ResourceBundle config = ResourceBundle.getBundle("DBConnection");
        DRIVER_NAME = config.getString("driverName");
        URL = config.getString("url");
        USER_NAME = config.getString("username");
        PASSWORD = config.getString("password");

        dataSource = setupDataSource();
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private static ComboPooledDataSource setupDataSource() {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass(DRIVER_NAME);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        cpds.setJdbcUrl(URL);
        cpds.setUser(USER_NAME);
        cpds.setPassword(PASSWORD);
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        return cpds;
    }
}
