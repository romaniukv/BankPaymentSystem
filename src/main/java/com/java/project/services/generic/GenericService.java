package com.java.project.services.generic;

import com.java.project.model.dao.generic.GenericDAO;

import java.util.List;

/**
 * {@link GenericDAO}
 */
public interface GenericService<T> {

    /**
     * {@link GenericDAO#selectAll()}
     */
    List<T> selectAll();

    /**
     * {@link GenericDAO#create(Object)}
     */
    boolean create(T entity);

    /**
     * {@link GenericDAO#update(Object)}
     */
    boolean update(T entity);

    /**
     * {@link GenericDAO#findByKey(int)}
     */
    T findByKey(int key);

    /**
     * {@link GenericDAO#deleteByKey(int)}
     */
    void deleteByKey(int key);
}
