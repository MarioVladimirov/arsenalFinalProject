package com.example.arsenalfinalproject.web;

import com.example.arsenalfinalproject.model.view.GamesScoreUserViewModel;
import com.example.arsenalfinalproject.model.view.UserViewModel;
import com.example.arsenalfinalproject.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@RestController
public class QuizRestController {

    private final GameService gameService;

    public QuizRestController(GameService gameService) {
        this.gameService = gameService;
    }


    @GetMapping("/api/games/quest")
    public ResponseEntity<List<UserViewModel>> getPointUser(Principal principal) {

        List<UserViewModel> scoreByUser = gameService.getScoreByUser(principal.getName());


        return ResponseEntity
                .ok(scoreByUser);

    }
}
