package com.example.arsenalfinalproject.service;

import com.example.arsenalfinalproject.model.entity.UserEntity;
import com.example.arsenalfinalproject.model.service.UserChangeProfileServiceModel;
import com.example.arsenalfinalproject.model.service.UserEditServiceModel;
import com.example.arsenalfinalproject.model.service.UserRegisterServiceModel;
import com.example.arsenalfinalproject.model.view.OrderAllByOneUserViewModel;
import com.example.arsenalfinalproject.model.view.UserEditView;
import com.example.arsenalfinalproject.model.view.UserViewModel;
import com.example.arsenalfinalproject.service.impl.ArsenalUser;

import java.util.List;
import java.util.Optional;

public interface UserService {



    boolean isUserNameFree(String userName);

    void registerUserAndLogin(UserRegisterServiceModel userRegisterServiceModel);

    boolean isEmailFree(String email);

    void initializeUsersAndRoles();

    Optional<UserEntity> findUserById(Long id);

    Optional<UserEntity> findByUsername(String userIdentifier);

    List<UserViewModel> getAllUsers();

    void editUserRole(Long idUser, String role);


    String getUsernameById(Long id);

    void changeProfileRole(UserChangeProfileServiceModel serviceModel);


    List<UserViewModel> findByKeyword(String keyword);

    UserEditView findByUsernameViewModel(String currentName);

    void updateUserProfile(UserEditServiceModel userEditServiceModel , String currentUser);

    boolean isExistId(Long id);

    UserEntity getUserEntityById(Long id);

    boolean isAdmin(String name);

//    List<OrderAllByOneUserViewModel> findAllOrderForOneUserByCurrentUser(String currentUser);
}
