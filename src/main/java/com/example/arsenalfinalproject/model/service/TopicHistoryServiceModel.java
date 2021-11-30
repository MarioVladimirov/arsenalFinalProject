package com.example.arsenalfinalproject.model.service;

import org.springframework.web.multipart.MultipartFile;

public class TopicHistoryServiceModel {

    private String title;
    private String description;
    private MultipartFile picture;


    public TopicHistoryServiceModel() {
    }

    public String getTitle() {
        return title;
    }

    public TopicHistoryServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TopicHistoryServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public TopicHistoryServiceModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }
}
