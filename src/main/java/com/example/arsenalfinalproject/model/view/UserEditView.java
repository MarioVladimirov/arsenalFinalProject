package com.example.arsenalfinalproject.model.view;

import java.time.LocalDate;

public class UserEditView {

    private String username;
    private String firstName;
    private String lastName;
    private LocalDate dateBirth;
    private String email;
    private String interest;
    private String favoritePlayer;
    private String loveTrip;
    private String description;

    public UserEditView() {
    }

    public String getUsername() {
        return username;
    }

    public UserEditView setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEditView setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEditView setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public UserEditView setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
        return this;
    }

    public String getInterest() {
        return interest;
    }

    public UserEditView setInterest(String interest) {
        this.interest = interest;
        return this;
    }

    public String getFavoritePlayer() {
        return favoritePlayer;
    }

    public UserEditView setFavoritePlayer(String favoritePlayer) {
        this.favoritePlayer = favoritePlayer;
        return this;
    }

    public String getLoveTrip() {
        return loveTrip;
    }

    public UserEditView setLoveTrip(String loveTrip) {
        this.loveTrip = loveTrip;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UserEditView setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEditView setEmail(String email) {
        this.email = email;
        return this;
    }
}
