package com.example.arsenalfinalproject.model.view;

import com.example.arsenalfinalproject.model.entity.enums.RoleNameEnum;

public class RoleView {

    private Long id;
    private String role;

    public RoleView() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
