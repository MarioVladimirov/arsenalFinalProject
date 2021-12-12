package com.example.arsenalfinalproject.model.binding;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Size(min = 10, max = 25)
    public String getTopic() {
        return Topic;
    }

    public NewsAddBindingModel setTopic(String topic) {
        Topic = topic;
        return this;
    }

    @NotEmpty
    @Size(min = 20, message = "Description must be more 20 characters")
    public String getDescription() {
        return description;
    }

    public NewsAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
