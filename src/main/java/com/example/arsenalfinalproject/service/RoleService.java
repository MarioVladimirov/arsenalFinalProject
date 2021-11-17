package com.example.arsenalfinalproject.service;

import com.example.arsenalfinalproject.model.entity.RoleEntity;
import com.example.arsenalfinalproject.model.view.RoleView;

import java.util.Optional;
import java.util.Set;

public interface RoleService {
    Set<RoleView> getAllRole();


    Set<RoleEntity> getRoleByName(String role);
}
