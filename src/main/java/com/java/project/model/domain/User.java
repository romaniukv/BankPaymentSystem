package com.java.project.model.domain;

/**
 * Represents user.
 */
public class User {

    /**
     * User's id.
     */
    private int id;

    /**
     * User's role(admin or user).
     */
    private Role role;

    /**
     * User's username.
     */
    private String username;

    /**
     * User's password.
     */
    private String password;

    /**
     * User's e-mail.
     */
    private String email;

    /**
     * User's first name.
     */
    private String firstName;

    /**
     * User's last name.
     */
    private String lastName;

    public User() {
    }

    public User(Role role, String username, String password, String email, String firstName, String lastName) {
        this.role = role;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public boolean isAdmin() {
        return role.equals(Role.ADMIN);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return lastName.equals(user.lastName) &&
                role == user.role &&
                username.equals(user.username) &&
                password.equals(user.password) &&
                email.equals(user.email) &&
                firstName.equals(user.firstName);
    }

    @Override
    public int hashCode() {
        int result = role.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }
}
