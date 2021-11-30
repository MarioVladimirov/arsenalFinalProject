package com.example.arsenalfinalproject.model.service;

import org.springframework.web.multipart.MultipartFile;

public class NewsAddServiceModel {

    private Long id;
    private MultipartFile picture;
    private String Topic;
    private String description;

    public NewsAddServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public NewsAddServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public NewsAddServiceModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }

    public String getTopic() {
        return Topic;
    }

    public NewsAddServiceModel setTopic(String topic) {
        Topic = topic;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public NewsAddServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
