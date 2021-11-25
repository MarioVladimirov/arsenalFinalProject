package com.example.arsenalfinalproject.repository;

import com.example.arsenalfinalproject.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByUsernameIgnoreCase(String username);

    Optional<UserEntity> findByEmailIgnoreCase(String email);

    Optional<UserEntity> findByUsername(String username);


    @Query(value = "select * from users  u where u.username like %:keyword%" , nativeQuery = true )
    List<UserEntity> findByKeyword(@Param("keyword") String keyword);


}
