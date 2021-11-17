package com.example.arsenalfinalproject.repository;

import com.example.arsenalfinalproject.model.entity.RoleEntity;
import com.example.arsenalfinalproject.model.entity.enums.RoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    RoleEntity findByRole(RoleNameEnum role);


    Set<RoleEntity> findAllByRole(RoleNameEnum role);

}
