package com.example.arsenalfinalproject.repository;

import com.example.arsenalfinalproject.model.entity.MemberTopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberTopicRepository extends JpaRepository<MemberTopicEntity,Long> {


    List<MemberTopicEntity> getAllByApprovedTrue();


}
