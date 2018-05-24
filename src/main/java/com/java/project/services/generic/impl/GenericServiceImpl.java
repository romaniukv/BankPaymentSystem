package com.java.project.services.generic.impl;

import com.java.project.model.dao.generic.GenericDAO;
import com.java.project.services.DBConnection;
import com.java.project.services.generic.GenericService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GenericServiceImpl<T> implements GenericService<T> {

    private GenericDAO<T> genericDAO;

    public GenericServiceImpl() {

    }

    public void setDAOImpl(GenericDAO<T> genericDAO) {
        this.genericDAO = genericDAO;
    }

    @Override
    public List<T> selectAll() {
        Connection connection = null;
        List<T> entities = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            genericDAO.setConnection(connection);
            entities = genericDAO.selectAll();
            connection.commit();
        } catch (SQLException e) {
            DBConnection.rollbackAndCloseConnection(connection);
        } finally {
            DBConnection.closeConnection(connection);
        }
        return entities;
    }

    @Override
    public boolean create(T entity) {
        Connection connection = null;
        boolean flag;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            genericDAO.setConnection(connection);
            flag = genericDAO.create(entity);
            connection.commit();
        } catch (SQLException e) {
            flag = false;
            DBConnection.rollbackAndCloseConnection(connection);
        } finally {
            DBConnection.closeConnection(connection);
        }
        return flag;
    }

    @Override
    public boolean update(T entity) {
        Connection connection = null;
        boolean flag;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            genericDAO.setConnection(connection);
            flag = genericDAO.update(entity);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
            DBConnection.rollbackAndCloseConnection(connection);
        } finally {
            DBConnection.closeConnection(connection);
        }
        return flag;
    }

    @Override
    public T findByKey(int key) {
        Connection connection = null;
        T entity = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            genericDAO.setConnection(connection);
            entity = genericDAO.findByKey(key);
            connection.commit();
        } catch (SQLException e) {
            DBConnection.rollbackAndCloseConnection(connection);
        } finally {
            DBConnection.closeConnection(connection);
        }
        return entity;
    }

    @Override
    public void deleteByKey(int key) {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            genericDAO.setConnection(connection);
            genericDAO.deleteByKey(key);
            connection.commit();
        } catch (SQLException e) {
            DBConnection.rollbackAndCloseConnection(connection);
        } finally {
            DBConnection.closeConnection(connection);
        }
    }
}
