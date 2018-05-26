package com.java.project.services;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnection {

    private static final Logger logger = LogManager.getLogger(DBConnection.class);

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

    public static void closeDataSource() {
        if (dataSource != null) {
            dataSource.close();
        }
        if (testDataSource != null) {
            testDataSource.close();
        }
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
            logger.error("Failed to close connection", e);
        }
    }

    public static void rollbackAndCloseConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
                connection.rollback();
            }
        } catch (SQLException e) {
            logger.error("Failed to rollback and close connection", e);
        }
    }

    public static void setTestMode() {
        mode = Mode.TEST;
    }

    public static void setApplicationMode() {
        mode = Mode.APPLICATION;
    }

}
