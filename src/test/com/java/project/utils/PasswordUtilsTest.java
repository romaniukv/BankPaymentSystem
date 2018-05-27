package com.java.project.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordUtilsTest {

    @Test
    public void checkPassword() {
        String password = "1hgfnd5t=";
        String encryptedPass = PasswordUtils.encryptPassword(password);

        boolean flag = PasswordUtils.checkPassword(password, encryptedPass);

        assertTrue(flag);
    }

    @Test
    public void encryptPassword() {
        String password = "1hgfnd5t=";
        String encryptedPass1 = PasswordUtils.encryptPassword(password);
        String encryptedPass2 = PasswordUtils.encryptPassword(password);

        assertNotEquals(encryptedPass1, encryptedPass2);
    }

}