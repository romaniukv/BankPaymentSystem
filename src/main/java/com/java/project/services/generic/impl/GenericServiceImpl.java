package com.java.project.services.generic.impl;

import com.java.project.model.dao.AbstractDAO;
import com.java.project.services.DBConnection;
import com.java.project.services.generic.GenericService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GenericServiceImpl<T> implements GenericService<T> {

    private AbstractDAO<T> abstractDAO;

    public GenericServiceImpl() {

    }

    public void setAbstractDAO(AbstractDAO<T> abstractDAO) {
        this.abstractDAO = abstractDAO;
    }

    @Override
    public List<T> selectAll() {
        Connection connection = null;
        List<T> entities = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            abstractDAO.setConnection(connection);
            entities = abstractDAO.selectAll();
            connection.commit();
        } catch (SQLException e) {
            DBConnection.rollbackAndCloseConnection(connection);
        }
        finally {
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
            abstractDAO.setConnection(connection);
            flag = abstractDAO.create(entity);
            connection.commit();
        } catch (SQLException e) {
            flag = false;
            DBConnection.rollbackAndCloseConnection(connection);
        }
        finally {
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
            abstractDAO.setConnection(connection);
            flag = abstractDAO.update(entity);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
            DBConnection.rollbackAndCloseConnection(connection);
        }
        finally {
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
            abstractDAO.setConnection(connection);
            entity = abstractDAO.findByKey(key);
            connection.commit();
        } catch (SQLException e) {
            DBConnection.rollbackAndCloseConnection(connection);
        }
        finally {
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
            abstractDAO.setConnection(connection);
            abstractDAO.deleteByKey(key);
            connection.commit();
        } catch (SQLException e) {
            DBConnection.rollbackAndCloseConnection(connection);
        }
        finally {
            DBConnection.closeConnection(connection);
        }
    }
}
