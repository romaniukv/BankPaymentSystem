package com.java.project.model.dao.generic;

import java.sql.Connection;
import java.util.List;

/**
 * Generic dao which unifies common operations.
 *
 * @param <T> entity class.
 */
public interface GenericDAO<T> {

    /**
     * Selects list of all entities from database.
     *
     * @return list of all entities.
     */
    List<T> selectAll();

    /**
     * Creates new entity in database.
     *
     * @param entity new entity
     * @return <code>true</code> if the entity is successfully created;
     * <code>false</code> otherwise
     */
    boolean create(T entity);

    /**
     * Updates entity in database.
     *
     * @param entity entity to update
     * @return <code>true</code> if the entity is successfully updated;
     * <code>false</code> otherwise
     */
    boolean update(T entity);

    /**
     * Searches entity in database by key.
     *
     * @param key the key
     * @return entity
     */
    T findByKey(int key);

    /**
     * Deletes entity in database by key
     *
     * @param key the key
     */
    void deleteByKey(int key);

    /**
     * Gives connection.
     *
     * @return connection
     */
    Connection getConnection();

    /**
     * Sets connection
     *
     * @param connection
     */
    void setConnection(Connection connection);

}
