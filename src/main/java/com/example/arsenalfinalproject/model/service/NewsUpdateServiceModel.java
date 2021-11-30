package com.example.arsenalfinalproject.model.service;

public class NewsUpdateServiceModel {


    private Long id;
    private String urlPictureNews;
    private String Topic;
    private String description;
    private boolean canDelete;

    public NewsUpdateServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public NewsUpdateServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUrlPictureNews() {
        return urlPictureNews;
    }

    public NewsUpdateServiceModel setUrlPictureNews(String urlPictureNews) {
        this.urlPictureNews = urlPictureNews;
        return this;
    }

    public String getTopic() {
        return Topic;
    }

    public NewsUpdateServiceModel setTopic(String topic) {
        Topic = topic;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public NewsUpdateServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public NewsUpdateServiceModel setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
        return this;
    }
}
