package com.example.arsenalfinalproject.service;

import com.example.arsenalfinalproject.model.entity.UserEntity;
import com.example.arsenalfinalproject.model.service.UserRegisterServiceModel;

import java.util.Optional;

public interface UserService {



    boolean isUserNameFree(String userName);

    void registerUser(UserRegisterServiceModel userRegisterServiceModel);

    boolean isEmailFree(String email);

    void initializeUsersAndRoles();

    Optional<UserEntity> findUserById(Long id);
}
