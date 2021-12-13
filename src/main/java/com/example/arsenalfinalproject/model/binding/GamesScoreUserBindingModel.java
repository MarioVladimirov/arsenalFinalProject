package com.example.arsenalfinalproject.model.binding;

import javax.validation.constraints.Positive;

public class GamesScoreUserBindingModel {


        private Integer score;
        private String username;

    public GamesScoreUserBindingModel() {
    }

    @Positive
    public Integer getScore() {
        return score;
    }

    public GamesScoreUserBindingModel setScore(Integer score) {
        this.score = score;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public GamesScoreUserBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }
}
