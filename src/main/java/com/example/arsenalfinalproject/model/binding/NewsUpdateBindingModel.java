package com.example.arsenalfinalproject.model.binding;

import javax.validation.constraints.NotBlank;

public class NewsUpdateBindingModel {

    private Long id;
    private String urlPictureNews;
    private String Topic;
    private String description;


    public NewsUpdateBindingModel() {
    }

    public String getUrlPictureNews() {
        return urlPictureNews;
    }

    public NewsUpdateBindingModel setUrlPictureNews(String urlPictureNews) {
        this.urlPictureNews = urlPictureNews;
        return this;
    }

    public Long getId() {
        return id;
    }

    public NewsUpdateBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    @NotBlank
    public String getTopic() {
        return Topic;
    }

    public NewsUpdateBindingModel setTopic(String topic) {
        Topic = topic;
        return this;
    }

    @NotBlank
    public String getDescription() {
        return description;
    }

    public NewsUpdateBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
