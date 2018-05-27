package com.java.project.model.dao.generic.impl;


import com.java.project.model.dao.generic.GenericDAO;
import com.java.project.model.domain.AccountStatus;
import com.java.project.model.domain.Role;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of GenericDAO
 * @param <T> entity
 */
public abstract class GenericDAOImpl<T> implements GenericDAO<T> {

    private static final Logger logger = LogManager.getLogger(GenericDAO.class);

    private final String FIND_ALL;
    private final String CREATE;
    private final String UPDATE;
    private final String FIND_BY_KEY;
    private final String DELETE_BY_KEY;
    private String[][] nameMapping;
    private Class<T> entityClass;
    private Connection connection;

    /**
     * Constructor for GenericDAOImpl. Used in subclasses.
     *
     * @param find_all      query string to find all entities.
     * @param create        query string to create entity.
     * @param update        query string to update entity.
     * @param find_by_key   query string to find entity by key
     * @param delete_by_key query string to delete entity by key
     * @param nameMapping   maps field in entity with column names in database.
     * @param entityClass   entity class.
     */
    public GenericDAOImpl(String find_all, String create, String update, String find_by_key, String delete_by_key, String[][] nameMapping, Class<T> entityClass) {
        FIND_ALL = find_all;
        CREATE = create;
        UPDATE = update;
        FIND_BY_KEY = find_by_key;
        DELETE_BY_KEY = delete_by_key;
        this.nameMapping = nameMapping;
        this.entityClass = entityClass;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<T> selectAll() {
        List<T> entities = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(FIND_ALL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                T entity = createEntityFromResultSet(rs);
                entities.add(entity);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            logger.error(e);
        }
        return entities;
    }

    @Override
    public boolean create(T entity) {
        boolean success = false;
        try (PreparedStatement ps = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            if (addParameters(entity, ps)) {
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    success = setEntityId(entity, rs.getInt(1));
                }
            }
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
            logger.error(e);
            success = false;
        }
        return success;
    }

    @Override
    public boolean update(T entity) {
        boolean flag = false;
        try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            addParameters(entity, ps);
            int indexOfLastParameter = ps.getParameterMetaData().getParameterCount();
            Field field = getField("id");
            if (field != null) {
                field.setAccessible(true);
                ps.setInt(indexOfLastParameter, field.getInt(entity));
                ps.execute();
                flag = true;
            }
        } catch (SQLException | IllegalAccessException e) {
            logger.error(e);
            flag = false;
        }
        return flag;
    }

    @Override
    public T findByKey(int key) {
        T entity = null;
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_KEY);) {
            ps.setInt(1, key);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                entity = createEntityFromResultSet(rs);
            }
        } catch (SQLException | IllegalAccessException | InstantiationException e) {
            logger.error(e);
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public void deleteByKey(int key) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_BY_KEY)) {
            ps.setInt(1, key);
            ps.execute();
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    private T createEntityFromResultSet(ResultSet rs) throws IllegalAccessException, InstantiationException, SQLException {
        T entity = entityClass.newInstance();
        setEntityId(entity, rs.getInt("id"));
        for (String[] parameterName : nameMapping) {
            String fieldType;
            Field field = getField(parameterName[0]);
            if (field != null) {
                fieldType = field.getType().getSimpleName();
                field.setAccessible(true);
                switch (fieldType) {
                    case "Role":
                        field.set(entity, Role.valueOf(rs.getString(parameterName[1])));
                        break;
                    case "AccountStatus":
                        field.set(entity, AccountStatus.valueOf(rs.getObject(parameterName[1]).toString()));
                        break;
                    default:
                        field.set(entity, rs.getObject(parameterName[1]));
                }
                field.setAccessible(true);
            }
        }
        return entity;
    }

    private boolean addParameters(T entity, PreparedStatement ps) throws SQLException, IllegalAccessException {
        int i = 1;
        for (String[] parameterName : nameMapping) {
            String fieldType;
            Field field = getField(parameterName[0]);
            if (field != null) {
                fieldType = field.getType().getSimpleName();
                field.setAccessible(true);
                switch (fieldType) {
                    case "Role":
                        ps.setString(i, field.get(entity).toString());
                        break;
                    case "AccountStatus":
                        ps.setString(i, field.get(entity).toString());
                        break;
                    case "BigDecimal":
                        ps.setBigDecimal(i, (BigDecimal) field.get(entity));
                        break;
                    default:
                        ps.setObject(i, field.get(entity));
                }
                i++;
            } else {
                return false;
            }
        }
        return true;
    }

    private Field getField(String fieldName) {
        Field field;
        try {
            field = entityClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            try {
                field = entityClass.getSuperclass().getDeclaredField(fieldName);
            } catch (NoSuchFieldException e1) {
                logger.error(e);
                return null;
            }
        }
        return field;
    }

    private boolean setEntityId(T entity, int id) throws IllegalAccessException {
        Field idField = getField("id");
        if (idField != null) {
            idField.setAccessible(true);
            idField.setInt(entity, id);
            return true;
        }
        return false;
    }

}
