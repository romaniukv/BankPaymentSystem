package com.epam.project.utils;

import com.epam.project.model.entities.Role;
import com.epam.project.model.entities.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class SecurityUtils {

    private static List<String> securityPages;

    static {
        securityPages = new ArrayList<>();
        securityPages.add("/adminPanel");
        securityPages.add("/accountManagement");
    }

    public static boolean isSecurityPage(HttpServletRequest request) {
        for (String securityPage: securityPages) {
            if (request.getRequestURI().endsWith(securityPage)) {
                request.getRequestURI();
                return true;
            }
        }
        return false;
    }

    public static boolean hasPermission(User loginedUser) {
        return loginedUser.getRole().equals(Role.ADMIN);
    }
}
