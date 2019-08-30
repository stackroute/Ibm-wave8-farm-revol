package com.djamware.springangularauth.models;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Login {
    @Id
    String email;
    String password;
    String role;

    public Login(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Login() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Login{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
