package com.java.project.utils;

import org.jasypt.util.password.BasicPasswordEncryptor;

public class PasswordUtils {

    private final static BasicPasswordEncryptor basicPasswordEncryptor = new BasicPasswordEncryptor();

    public static String encryptPassword(String password){
        return basicPasswordEncryptor.encryptPassword(password);
    }

    public static boolean checkPassword(String password, String encryptedPassword){
        return basicPasswordEncryptor.checkPassword(password,encryptedPassword);
    }

}
