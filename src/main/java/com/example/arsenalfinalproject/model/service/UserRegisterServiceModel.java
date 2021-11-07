package com.example.arsenalfinalproject.model.service;

import java.time.LocalDate;

public class UserRegisterServiceModel {

    private String username;
    private String firstName;
    private String lastName;
    private LocalDate dateBirth;
    private String email;
    private String password;

    public UserRegisterServiceModel() {
    }

    public String getUsername() {
        return username != null ? username.trim() : null;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
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
}
