package com.example.arsenalfinalproject.repository;

import com.example.arsenalfinalproject.model.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity,Long> {

}
