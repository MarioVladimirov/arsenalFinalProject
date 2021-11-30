package com.example.arsenalfinalproject.model.binding;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NewsAddBindingModel {

    private MultipartFile picture;
    private String Topic;
    private String description;

    public NewsAddBindingModel() {
    }

    @NotNull
    public MultipartFile getPicture() {
        return picture;
    }

    public NewsAddBindingModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }

    @NotEmpty
    public String getTopic() {
        return Topic;
    }

    public NewsAddBindingModel setTopic(String topic) {
        Topic = topic;
        return this;
    }

    @NotEmpty
    public String getDescription() {
        return description;
    }

    public NewsAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
