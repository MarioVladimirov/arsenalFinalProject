package com.example.arsenalfinalproject.model.binding;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class UserEditBindingModel {


    private String username;
    private String firstName;
    private String lastName;
    private LocalDate dateBirth;
    private String email;
    private String interest;
    private String favoritePlayer;
    private String loveTrip;
    private String description;



    public String getUsername() {
        return username;
    }

    public UserEditBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    @NotNull
    @Size(min = 2, max = 20)
    public String getFirstName() {
        return firstName;
    }

    public UserEditBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @NotNull
    @Size(min = 2, max = 20)
    public String getLastName() {
        return lastName;
    }

    public UserEditBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public UserEditBindingModel setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
        return this;
    }

    @Size(min = 10)
    public String getInterest() {
        return interest;
    }

    public UserEditBindingModel setInterest(String interest) {
        this.interest = interest;
        return this;
    }

    @Size(min = 10)
    public String getFavoritePlayer() {
        return favoritePlayer;
    }

    public UserEditBindingModel setFavoritePlayer(String favoritePlayer) {
        this.favoritePlayer = favoritePlayer;
        return this;
    }

    @Size(min = 10)
    public String getLoveTrip() {
        return loveTrip;
    }

    public UserEditBindingModel setLoveTrip(String loveTrip) {
        this.loveTrip = loveTrip;
        return this;
    }

    @Size(min = 10)
    public String getDescription() {
        return description;
    }

    public UserEditBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public UserEditBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }
}
