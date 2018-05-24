package com.java.project.services;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnection {
    private static final DBConnection INSTANCE = new DBConnection();

    private static ComboPooledDataSource dataSource;

    private static ComboPooledDataSource testDataSource;

    private static Mode mode = Mode.APPLICATION;

    private enum Mode {
        APPLICATION, TEST
    }

    private DBConnection() {
        dataSource = setupDataSource("db-config");
        testDataSource = setupDataSource("test-db-config");
    }

    private ComboPooledDataSource setupDataSource(String properties) {
        ResourceBundle config = ResourceBundle.getBundle(properties);

        String driverName = config.getString("driverName");
        String url = config.getString("url");
        String username = config.getString("username");
        String password = config.getString("password");

        ComboPooledDataSource cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass(driverName);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        cpds.setJdbcUrl(url);
        cpds.setUser(username);
        cpds.setPassword(password);
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        return cpds;
    }

    public static DBConnection getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        if (mode.equals(Mode.APPLICATION)) {
            return dataSource.getConnection();
        }
        else {
            return testDataSource.getConnection();
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
        }
    }

    public static void rollbackAndCloseConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
                connection.rollback();
            }
        } catch (SQLException e) {
        }
    }

    public static void setTestMode() {
        mode = Mode.TEST;
    }

    public static void setApplicationMode() {
        mode = Mode.APPLICATION;
    }

}
