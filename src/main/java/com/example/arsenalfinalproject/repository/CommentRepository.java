package com.example.arsenalfinalproject.repository;

import com.example.arsenalfinalproject.model.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity , Long> {

    @Query ("select c from CommentEntity c LEFT JOIN FETCH c.author")
    List<CommentEntity> findAllByNewsId(Long news_id);


}
