package com.java.project.model.dao.impl;

import com.java.project.model.dao.UserDAO;
import com.java.project.model.dao.generic.impl.GenericDAOImpl;
import com.java.project.model.domain.Role;
import com.java.project.model.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

    private static final String FIND_BY_USERNAME = "SELECT * FROM users WHERE username = ?";

    public UserDAOImpl() {
        super("SELECT * FROM users;",
                "INSERT INTO users (role, username, password, email, first_name, last_name) VALUES(?, ?, ?, ?, ?, ?);",
                "UPDATE users SET role = ?, username = ?, password = ?, email = ?, first_name = ?, last_name = ? WHERE id = ?;",
                "SELECT * FROM users WHERE id = ?;",
                "DELETE FROM users WHERE id = ?;",
                new String[][]{{"role", "role"},
                        {"username", "username"},
                        {"password", "password"},
                        {"email", "email"},
                        {"firstName", "first_name"},
                        {"lastName", "last_name"}},
                User.class);
    }

    @Override
    public User findUserByUsername(String username) {
        User user = null;
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_USERNAME)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(Role.valueOf(rs.getString(2)), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7));
                user.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


}