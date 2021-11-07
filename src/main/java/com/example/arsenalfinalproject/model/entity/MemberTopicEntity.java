package com.example.arsenalfinalproject.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "membersTopic")
public class MemberTopicEntity extends BaseEntity {

    private String title;
    private String description;
    private String urlTopicPicture;


    public MemberTopicEntity() {
    }

    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(nullable = false , columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = false)
    public String getUrlTopicPicture() {
        return urlTopicPicture;
    }

    public void setUrlTopicPicture(String urlTopicPicture) {
        this.urlTopicPicture = urlTopicPicture;
    }
}
