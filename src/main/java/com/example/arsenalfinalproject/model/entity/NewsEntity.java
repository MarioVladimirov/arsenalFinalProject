package com.example.arsenalfinalproject.model.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "news")
public class NewsEntity extends BaseEntity{

    private PictureEntity picture;
    private String Topic;
    private String description;
    private UserEntity user;
    private LocalDate localDateNews;

    public NewsEntity() {
    }


    @Column(nullable = false)
    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    @Column(nullable = false ,columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Column(nullable = false)
    public LocalDate getLocalDateNews() {
        return localDateNews;
    }

    public void setLocalDateNews(LocalDate localDateNews) {
        this.localDateNews = localDateNews;
    }

    @OneToOne
    public PictureEntity getPicture() {
        return picture;
    }

    public NewsEntity setPicture(PictureEntity picture) {
        this.picture = picture;
        return this;
    }
}
