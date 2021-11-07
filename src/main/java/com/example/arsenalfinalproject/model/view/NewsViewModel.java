package com.example.arsenalfinalproject.model.view;

import com.example.arsenalfinalproject.model.entity.UserEntity;

import java.time.LocalDate;

public class NewsViewModel {


    private String urlPictureNews;
    private String Topic;
    private String description;
    private UserEntity user;
    private LocalDate localDate;

    public NewsViewModel() {
    }

    public String getUrlPictureNews() {
        return urlPictureNews;
    }

    public void setUrlPictureNews(String urlPictureNews) {
        this.urlPictureNews = urlPictureNews;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
