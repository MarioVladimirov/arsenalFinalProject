package com.example.arsenalfinalproject.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "news")
public class NewsEntity extends BaseEntity{

    private PictureEntity picture;
    private String Topic;
    private String description;
    private UserEntity user;
    private LocalDate localDateNews;
    private List<CommentEntity> comments;

    public NewsEntity() {
    }


    @Column(nullable = false)
    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    @Column(nullable = false )
    @Lob
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

    @OneToMany(mappedBy = "news" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    public List<CommentEntity> getComments() {
        return comments;
    }

    public NewsEntity setComments(List<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }
}
