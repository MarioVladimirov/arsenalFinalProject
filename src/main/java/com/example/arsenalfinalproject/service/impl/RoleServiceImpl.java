package com.example.arsenalfinalproject.service.impl;


import com.example.arsenalfinalproject.model.entity.RoleEntity;
import com.example.arsenalfinalproject.model.entity.enums.RoleNameEnum;
import com.example.arsenalfinalproject.model.view.RoleView;
import com.example.arsenalfinalproject.repository.RoleRepository;
import com.example.arsenalfinalproject.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

   private final RoleRepository roleRepository;
   private final ModelMapper modelMapper;


    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<RoleView> getAllRole() {
        return roleRepository
                .findAll()
                .stream()
                .map(roleEntity -> modelMapper.map(roleEntity,RoleView.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<RoleEntity> getRoleByName(String role) {
        return roleRepository.findAllByRole(RoleNameEnum.valueOf(role));
    }


}
