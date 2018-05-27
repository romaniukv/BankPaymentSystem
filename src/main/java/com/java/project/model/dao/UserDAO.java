package com.java.project.model.dao;

import com.java.project.model.dao.generic.GenericDAO;
import com.java.project.model.domain.User;

/**
 * DAO for user.
 */
public interface UserDAO extends GenericDAO<User> {

    /**
     * Searches user by username.
     * @param username username
     * @return user
     */
    User findUserByUsername(String username);

}
