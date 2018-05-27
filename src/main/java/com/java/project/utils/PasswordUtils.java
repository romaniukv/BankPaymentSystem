package com.java.project.utils;

import org.jasypt.util.password.BasicPasswordEncryptor;

/**
 * Encrypts passwords.
 */
public class PasswordUtils {

    private final static BasicPasswordEncryptor basicPasswordEncryptor = new BasicPasswordEncryptor();

    /**
     * Encrypts password.
     * @param password password
     * @return encryptred password
     */
    public static String encryptPassword(String password){
        return basicPasswordEncryptor.encryptPassword(password);
    }

    /**
     * Checks if two passwords are the same.
     * @param password inputed by user password
     * @param encryptedPassword encrypted password from database.
     * @return <code>true</code> if passwords are the same
     * <code>false</code> otherwise.
     */
    public static boolean checkPassword(String password, String encryptedPassword){
        return basicPasswordEncryptor.checkPassword(password,encryptedPassword);
    }

}
