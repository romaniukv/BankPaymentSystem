package com.java.project.model.dao;


import com.java.project.model.domain.AccountStatus;
import com.java.project.model.domain.Role;
import com.java.project.services.DBConnection;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<T> implements DAO<T> {

    private final String FIND_ALL;
    private final String CREATE;
    private final String UPDATE;
    private final String FIND_BY_KEY;
    private final String DELETE_BY_KEY;
    private String[][] nameMapping;
    private Class<T> entityClass;

    public AbstractDAO(String find_all, String create, String update, String find_by_key, String delete_by_key, String[][] nameMapping, Class<T> entityClass) {
        FIND_ALL = find_all;
        CREATE = create;
        UPDATE = update;
        FIND_BY_KEY = find_by_key;
        DELETE_BY_KEY = delete_by_key;
        this.nameMapping = nameMapping;
        this.entityClass = entityClass;
    }

    @Override
    public List<T> selectAll() {
        List<T> entities = new ArrayList<>();
        try(Connection connection = DBConnection.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(FIND_ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                T entity = createEntityFromResultSet(rs);
                entities.add(entity);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return entities;
    }

    @Override
    public boolean create(T entity) {
        boolean success = false;
        try(Connection connection = DBConnection.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            if(addParameters(entity, ps)) {
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    success = setEntityId(entity, rs.getInt(1));
                }
            }
        }catch (SQLIntegrityConstraintViolationException e) {
            success = false;
        } catch (SQLException | IllegalAccessException e) {
            success = false;
        }
        return success;
    }

    @Override
    public boolean update(T entity) {
        boolean flag = false;
        try(Connection connection = DBConnection.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(UPDATE);
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
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public T findByKey(int key) {
        T entity = null;
        try(Connection connection = DBConnection.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(FIND_BY_KEY);
            ps.setInt(1,key);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                entity = createEntityFromResultSet(rs);
            }
        } catch (SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public void deleteByKey(int key) {
        try(Connection connection = DBConnection.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(DELETE_BY_KEY);
            ps.setInt(1,key);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
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
        for (String[] parameterName: nameMapping) {
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
            }
            else {
                return false;
            }
        }
        return  true;
    }

    private Field getField(String fieldName) {
        Field field;
        try {
            field = entityClass.getDeclaredField(fieldName);
        }
        catch (NoSuchFieldException e) {
            try {
                field = entityClass.getSuperclass().getDeclaredField(fieldName);
            } catch (NoSuchFieldException e1) {
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
