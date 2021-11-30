package com.example.arsenalfinalproject.model.entity;


import javax.persistence.*;

@Entity
@Table(name = "games")
public class GameEntity extends BaseEntity{

    private UserEntity user;
    private Integer score;


    public GameEntity() {
    }

    @OneToOne
    public UserEntity getUser() {
        return user;
    }

    public GameEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public Integer getScore() {
        return score;
    }

    public GameEntity setScore(Integer score) {
        this.score = score;
        return this;
    }
}
