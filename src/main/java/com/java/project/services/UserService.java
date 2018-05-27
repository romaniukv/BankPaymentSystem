package com.java.project.services;

import com.java.project.model.dao.UserDAO;
import com.java.project.model.domain.User;
import com.java.project.services.generic.GenericService;

/**
 * Service for user dao.
 */
public interface UserService extends GenericService<User> {

    /**
     * {@link UserDAO#findUserByUsername(String)}
     */
    User findUserByUsername(String username);
}
