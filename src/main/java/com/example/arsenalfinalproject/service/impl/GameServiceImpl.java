package com.example.arsenalfinalproject.service.impl;

import com.example.arsenalfinalproject.model.entity.GameEntity;
import com.example.arsenalfinalproject.model.entity.UserEntity;
import com.example.arsenalfinalproject.model.view.GamesScoreUserViewModel;
import com.example.arsenalfinalproject.model.view.UserViewModel;
import com.example.arsenalfinalproject.repository.GameRepository;
import com.example.arsenalfinalproject.service.GameService;
import com.example.arsenalfinalproject.service.UserService;
import com.example.arsenalfinalproject.web.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<UserViewModel> getScoreByUser(String userName) {
//        Optional<UserEntity> byUsername = userService.findByUsername(userName);
//
//        Optional<GameEntity> byUserId = gameRepository.findByUserId(byUsername.get().getId());
//
//        GamesScoreUserViewModel gamesScoreUserViewModel = new GamesScoreUserViewModel();
//        if (byUserId.isEmpty()) {
//
//        gamesScoreUserViewModel.setScore(0);
//        }else {
//            gamesScoreUserViewModel.setScore(byUserId.get().getScore());
//        }
        List<UserViewModel> allUsers = userService.getAllUsers();

        return allUsers;
    }
}
