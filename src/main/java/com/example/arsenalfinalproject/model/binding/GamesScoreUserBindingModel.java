package com.example.arsenalfinalproject.model.binding;

import javax.validation.constraints.Positive;

public class GamesScoreUserBindingModel {


        private Integer score;

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
}
