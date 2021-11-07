package com.example.arsenalfinalproject.service;

import com.example.arsenalfinalproject.model.service.UserRegisterServiceModel;

public interface UserService {



    boolean isUserNameFree(String userName);

    void registerUser(UserRegisterServiceModel userRegisterServiceModel);

    boolean isEmailFree(String email);
}
