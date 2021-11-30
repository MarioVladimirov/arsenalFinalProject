package com.example.arsenalfinalproject.model.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity {

    private Boolean approved;
    private String textContent;
    private LocalDateTime created;
    private UserEntity author;
    private NewsEntity news;


    public CommentEntity() {
    }

    @Column(nullable = false)
    public Boolean getApproved() {
        return approved;
    }

    public CommentEntity setApproved(Boolean approved) {
        this.approved = approved;
        return this;
    }

    @Column
    public String getTextContent() {
        return textContent;
    }

    public CommentEntity setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    @Column(name = "created", nullable = false)
    public LocalDateTime getCreated() {
        return created;
    }

    public CommentEntity setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    @ManyToOne
    public UserEntity getAuthor() {
        return author;
    }

    public CommentEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    @ManyToOne
    public NewsEntity getNews() {
        return news;
    }

    public CommentEntity setNews(NewsEntity news) {
        this.news = news;
        return this;
    }
}
