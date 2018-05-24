package com.java.project.model.dao;

import com.java.project.model.dao.generic.GenericDAO;
import com.java.project.model.domain.User;

public interface UserDAO extends GenericDAO<User> {

    User findUserByUsername(String username);

}
