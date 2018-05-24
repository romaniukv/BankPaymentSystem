package com.java.project.model.dao.generic;

import java.sql.Connection;
import java.util.List;

public interface GenericDAO<T> {

    List<T> selectAll();

    boolean create(T entity);

    boolean update(T entity);

    T findByKey(int key);

    void deleteByKey(int key);

    Connection getConnection();

    void setConnection(Connection connection);

}
