package com.example.arsenalfinalproject.model.service;

import java.time.LocalDate;

public class UserEditServiceModel {

    private String username;
    private String firstName;
    private String lastName;
    private LocalDate dateBirth;
    private String email;
    private String interest;
    private String favoritePlayer;
    private String loveTrip;
    private String description;


    public UserEditServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserEditServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEditServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEditServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public UserEditServiceModel setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEditServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getInterest() {
        return interest;
    }

    public UserEditServiceModel setInterest(String interest) {
        this.interest = interest;
        return this;
    }

    public String getFavoritePlayer() {
        return favoritePlayer;
    }

    public UserEditServiceModel setFavoritePlayer(String favoritePlayer) {
        this.favoritePlayer = favoritePlayer;
        return this;
    }

    public String getLoveTrip() {
        return loveTrip;
    }

    public UserEditServiceModel setLoveTrip(String loveTrip) {
        this.loveTrip = loveTrip;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UserEditServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }



}
