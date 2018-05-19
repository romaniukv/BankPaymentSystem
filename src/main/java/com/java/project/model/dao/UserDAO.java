package com.java.project.model.dao;

import com.java.project.model.domain.Role;
import com.java.project.model.domain.User;
import com.java.project.services.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends AbstractDAO<User> {

    private static final String FIND_BY_USERNAME_AND_PASS = "SELECT * FROM users WHERE username = ? AND password = ?";

    public UserDAO() {
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

    public User findUserByUsernameAndPassword(String username, String password) {
        User user = null;
        try(Connection connection = DBConnection.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(FIND_BY_USERNAME_AND_PASS);
            ps.setString(1, username);
            ps.setString(2, password);
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