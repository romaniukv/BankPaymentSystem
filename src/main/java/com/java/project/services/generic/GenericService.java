package com.java.project.services.generic;

import java.util.List;

public interface GenericService<T> {

    List<T> selectAll();
    boolean create(T entity);
    boolean update(T entity);
    T findByKey(int key);
    void deleteByKey(int key);
}
