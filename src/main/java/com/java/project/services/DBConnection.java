package com.java.project.services;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnection {
    private static final DBConnection INSTANCE = new DBConnection();

    private static ComboPooledDataSource comboPooledDataSource;

    private Mode mode = Mode.APPLICATION;

    private enum Mode {
        APPLICATION, TEST
    }

    private DBConnection() {
        comboPooledDataSource = setupDataSource();
    }


    private ComboPooledDataSource setupDataSource() {
        ResourceBundle config;

        if (mode.equals(Mode.APPLICATION)) {
            config = ResourceBundle.getBundle("db-config");
        }
        else {
            config = ResourceBundle.getBundle("test-db-config");
        }

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
        return comboPooledDataSource.getConnection();
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

}
