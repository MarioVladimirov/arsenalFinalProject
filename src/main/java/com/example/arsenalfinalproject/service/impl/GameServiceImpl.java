package com.example.arsenalfinalproject.service.impl;

import com.example.arsenalfinalproject.model.entity.GameEntity;
import com.example.arsenalfinalproject.model.entity.UserEntity;
import com.example.arsenalfinalproject.model.service.GameUpdateService;
import com.example.arsenalfinalproject.model.view.GameViewModel;
import com.example.arsenalfinalproject.repository.GameRepository;
import com.example.arsenalfinalproject.service.GameService;
import com.example.arsenalfinalproject.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final UserService userService;

    public GameServiceImpl(GameRepository gameRepository, UserService userService) {
        this.gameRepository = gameRepository;
        this.userService = userService;
    }

    @Override
    public GameViewModel getScoreByUser(String userName) {
        Optional<UserEntity> byUsername = userService.findByUsername(userName);

        Optional<GameEntity> byUserId = gameRepository.findByUserId(byUsername.get().getId());

        GameViewModel gameViewModel = new GameViewModel();
        if (byUserId.isEmpty()) {

        gameViewModel.setScore(0);
        gameViewModel.setUsername(userName);
        GameEntity gameEntity = new GameEntity();
        gameEntity.setScore(0);
        gameEntity.setUser(byUsername.get());
        gameRepository.save(gameEntity);
        }else {
            gameViewModel.setScore(byUserId.get().getScore());
            gameViewModel.setUsername(userName);
        }
//
        return gameViewModel;
    }

    @Override
    public void updateScore(GameUpdateService gameUpdateService) {

        Optional<UserEntity> currentUsername = userService.findByUsername(gameUpdateService.getUsername());

        Optional<GameEntity> currentGame = gameRepository
                .findByUserId(currentUsername.get().getId());

        currentGame.get().setScore(gameUpdateService.getScore());

        gameRepository.save(currentGame.get());

    }

//    @Override
//    public GameViewModel getScoreByUsername(String name) {
//        UserEntity userEntity = userService.findByUsername(name).get();
//
//        GameViewModel gameViewModel = new GameViewModel();
//        gameViewModel.setUsername(userEntity.getUsername());
//        var score = gameRepository.findByUserId(userEntity.getId());
//
//        gameViewModel.setScore(score.isEmpty() ? 0 : score.get().getScore());
//
//        return gameViewModel;
//    }
}
