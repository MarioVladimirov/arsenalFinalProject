package com.example.arsenalfinalproject.repository;

import com.example.arsenalfinalproject.model.entity.GameEntity;
import com.example.arsenalfinalproject.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<GameEntity , Long>{

    Optional<GameEntity> findByUserId(Long user_id);



}
