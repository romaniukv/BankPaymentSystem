package com.epam.project.dao;

import java.util.List;

public interface DAO <T> {

    List<T> findAll();
    boolean create(T entity);
    void update(T entity);
    T findByKey(int key);
    void deleteByKey(int key);
}
