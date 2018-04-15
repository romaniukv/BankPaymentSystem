package com.epam.project.model.entities;

import com.epam.project.config.Role;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;

    private Role role;

    private String username;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private List<Account> accounts;

    public User() {
    }

    public User(Role role, String username, String password, String email, String firstName, String lastName) {
        accounts = new ArrayList<>();
        this.role = role;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int countCreditAccounts() {
        int count = 0;
        for (Account account: accounts) {
            if (account instanceof CreditAccount) {
                count++;
            }
        }
        return count;
    }

    public int countDepositAccounts() {
        int count = 0;
        for (Account account: accounts) {
            if (account instanceof DepositAccount) {
                count++;
            }
        }
        return count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
