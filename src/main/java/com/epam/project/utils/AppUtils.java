package com.epam.project.utils;

import com.epam.project.model.entities.User;

import javax.servlet.http.HttpSession;

public class AppUtils {

    public static User getLoginedUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }

}
