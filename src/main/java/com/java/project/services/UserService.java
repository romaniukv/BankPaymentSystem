package com.java.project.services;

import com.java.project.model.domain.User;
import com.java.project.services.generic.GenericService;


public interface UserService extends GenericService<User> {

    User findUserByUsername(String username);
}
