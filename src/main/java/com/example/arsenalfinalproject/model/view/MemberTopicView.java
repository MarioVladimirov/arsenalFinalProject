package com.example.arsenalfinalproject.model.view;

import com.example.arsenalfinalproject.model.entity.PictureEntity;
import com.example.arsenalfinalproject.model.entity.UserEntity;

public class MemberTopicView {

    private String username;
    private String title;
    private String description;
    private String urlPicture;


    public MemberTopicView() {
    }

    public String getUsername() {
        return username;
    }

    public MemberTopicView setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public MemberTopicView setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MemberTopicView setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public MemberTopicView setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
        return this;
    }
}
