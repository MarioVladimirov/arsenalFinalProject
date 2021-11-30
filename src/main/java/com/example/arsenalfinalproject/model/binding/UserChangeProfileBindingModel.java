package com.example.arsenalfinalproject.model.binding;

import com.example.arsenalfinalproject.model.validator.UniqueUserName;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserChangeProfileBindingModel {


    private Long id;
    private String username;
    private String role;


    public UserChangeProfileBindingModel() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Size(min = 3, max = 20 , message = "Username should be between 3 and 20 characters")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
