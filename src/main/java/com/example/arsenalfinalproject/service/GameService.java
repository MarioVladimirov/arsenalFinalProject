package com.example.arsenalfinalproject.service;

import com.example.arsenalfinalproject.model.service.GameUpdateService;
import com.example.arsenalfinalproject.model.view.GameViewModel;

public interface GameService {

    GameViewModel getScoreByUser(String userName);


    void updateScore(GameUpdateService gameUpdateService);
}
