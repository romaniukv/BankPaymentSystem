package com.epam.project;

import com.epam.project.config.Role;
import com.epam.project.dao.UserDAO;
import com.epam.project.model.entities.User;

public class Main {

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        User user = new User(Role.USER, "121", "121", "121", "121", "121");
        userDAO.create(user);

    }
}
