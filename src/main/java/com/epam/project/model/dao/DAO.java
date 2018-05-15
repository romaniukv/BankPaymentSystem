package com.epam.project.model.dao;

import java.util.List;

public interface DAO <T> {

    List<T> selectAll();
    boolean create(T entity);
    boolean update(T entity);
    T findByKey(int key);
    void deleteByKey(int key);
}
