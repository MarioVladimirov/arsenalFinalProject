package com.example.arsenalfinalproject.service.impl;

import com.example.arsenalfinalproject.model.entity.RoleEntity;
import com.example.arsenalfinalproject.model.entity.UserEntity;
import com.example.arsenalfinalproject.model.entity.enums.RoleNameEnum;
import com.example.arsenalfinalproject.model.service.UserRegisterServiceModel;
import com.example.arsenalfinalproject.repository.RoleRepository;
import com.example.arsenalfinalproject.repository.UserRepository;
import com.example.arsenalfinalproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }



    @Override
    public void registerUser(UserRegisterServiceModel userRegisterServiceModel) {

        RoleEntity roleUser = roleRepository.findByRole(RoleNameEnum.USER);

        UserEntity newUser = modelMapper.map(userRegisterServiceModel,UserEntity.class);
        newUser.setRoles(Set.of(roleUser));

        userRepository.save(newUser);

    }

    @Override
    public boolean isUserNameFree(String userName) {
        return userRepository.findByUsernameIgnoreCase(userName).isEmpty();
    }

    @Override
    public boolean isEmailFree(String email) {
        return userRepository.findByEmailIgnoreCase(email).isEmpty();
    }


}
