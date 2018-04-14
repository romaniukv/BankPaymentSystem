package com.epam.project.dao;


import com.epam.project.utils.DBConnection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<T> implements DAO<T> {

    private final String FIND_ALL;
    private final String CREATE;
    private final String UPDATE;
    private final String FIND_BY_KEY;
    private final String DELETE_BY_KEY;
    private String[] parametersNames;
    private Class<T> entityClass;

    public AbstractDAO(String find_all, String create, String update, String find_by_key, String delete_by_key, String[] parametersNames, Class<T> entityClass) {
        FIND_ALL = find_all;
        CREATE = create;
        UPDATE = update;
        FIND_BY_KEY = find_by_key;
        DELETE_BY_KEY = delete_by_key;
        this.parametersNames = parametersNames;
        this.entityClass = entityClass;
    }

    @Override
    public List<T> findAll() {
        List<T> entities = new ArrayList<>();
        try(Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(FIND_ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                T entity = entityClass.getConstructor().newInstance();
                setEntityId(entity, rs.getInt("id"));
                for (String parametersName : parametersNames) {
                    Field field = entityClass.getDeclaredField(parametersName);
                    field.setAccessible(true);
                    field.set(entity, rs.getObject(parametersName));
                }
                entities.add(entity);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return entities;
    }

    @Override
    public void create(T entity) {
        try(Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            addParameters(entity, ps);
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next())
                setEntityId(entity, rs.getInt(1));
        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(T entity) {
        try(Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(UPDATE);
            addParameters(entity, ps);
            int indexOfLastParameter = ps.getParameterMetaData().getParameterCount();
            Field field = entityClass.getDeclaredField("id");
            field.setAccessible(true);
            ps.setInt(indexOfLastParameter, field.getInt(entity));
            ps.execute();
        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T findByKey(int key) {
        T entity = null;
        try(Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(FIND_BY_KEY);
            ps.setInt(1,key);
            ResultSet rs = ps.executeQuery();
            entity = entityClass.newInstance();
            while (rs.next()) {
                setEntityId(entity, rs.getInt("id"));
                for (String parametersName : parametersNames) {
                    Field field = entityClass.getDeclaredField(parametersName);
                    field.setAccessible(true);
                    field.set(entity, rs.getObject(parametersName));
                }
            }
        } catch (SQLException | NoSuchFieldException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public void deleteByKey(int key) {
        try(Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(DELETE_BY_KEY);
            ps.setInt(1,key);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void addParameters(T entity, PreparedStatement ps) throws SQLException, NoSuchFieldException, IllegalAccessException {
        for (int i = 0; i < parametersNames.length; i++) {
            String valueType = entity.getClass().getDeclaredField(parametersNames[i]).getType().getSimpleName();
            Field field = entity.getClass().getDeclaredField(parametersNames[i]);
            field.setAccessible(true);
            switch (valueType) {
                case "int":
                    ps.setInt(i + 1, field.getInt(entity));
                    break;
                case "float":
                    ps.setFloat(i + 1, field.getFloat(entity));
                    break;
                case "boolean":
                    ps.setBoolean(i + 1, field.getBoolean(entity));
                    break;
                case "String":
                    ps.setString(i + 1, (String) field.get(entity));
                    break;
            }

        }

    }

    private void setEntityId(T entity, int id) throws NoSuchFieldException, IllegalAccessException {
        Field idField = entityClass.getDeclaredField("id");
        idField.setAccessible(true);
        idField.setInt(entity, id);
    }

}
