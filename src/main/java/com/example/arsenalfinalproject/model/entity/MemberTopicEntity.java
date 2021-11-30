package com.example.arsenalfinalproject.model.entity;


import javax.persistence.*;

@Entity
@Table(name = "membersTopic")
public class MemberTopicEntity extends BaseEntity {

    private UserEntity user;
    private String title;
    private String description;
    private PictureEntity picture;
    private boolean isApproved;


    public MemberTopicEntity() {
    }

    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    public PictureEntity getPicture() {
        return picture;
    }

    public MemberTopicEntity setPicture(PictureEntity picture) {
        this.picture = picture;
        return this;
    }

    @Column(nullable = false)
    public boolean isApproved() {
        return isApproved;
    }

    public MemberTopicEntity setApproved(boolean approved) {
        isApproved = approved;
        return this;
    }

    @ManyToOne
    public UserEntity getUser() {
        return user;
    }

    public MemberTopicEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
