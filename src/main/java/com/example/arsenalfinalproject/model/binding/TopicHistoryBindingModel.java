package com.example.arsenalfinalproject.model.binding;

import com.example.arsenalfinalproject.model.entity.PictureEntity;
import com.example.arsenalfinalproject.model.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TopicHistoryBindingModel {


    private String title;
    private String description;
    private MultipartFile picture;


    public TopicHistoryBindingModel() {
    }

    @NotEmpty
    @Size(min = 10, max = 25)
    public String getTitle() {
        return title;
    }

    public TopicHistoryBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    @NotEmpty
    @Size(min = 20, message = "Description must be more 20 characters")
    public String getDescription() {
        return description;
    }

    public TopicHistoryBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @NotNull
    public MultipartFile getPicture() {
        return picture;
    }

    public TopicHistoryBindingModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }
}
