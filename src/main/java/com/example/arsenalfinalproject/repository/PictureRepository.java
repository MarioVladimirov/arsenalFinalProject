package com.example.arsenalfinalproject.repository;

import com.example.arsenalfinalproject.model.entity.PictureEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<PictureEntity, Long> {

//
    PictureEntity findByPublicId(String publicId);
//
//    void delete(Long id);
}
