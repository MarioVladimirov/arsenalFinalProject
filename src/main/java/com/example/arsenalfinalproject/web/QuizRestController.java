package com.example.arsenalfinalproject.web;

import com.example.arsenalfinalproject.model.binding.GamesScoreUserBindingModel;
import com.example.arsenalfinalproject.model.service.GameUpdateService;
import com.example.arsenalfinalproject.model.view.GameViewModel;
import com.example.arsenalfinalproject.service.CommentService;
import com.example.arsenalfinalproject.service.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class QuizRestController {

    private final GameService gameService;
 private final CommentService commentService;
 private final ModelMapper modelMapper;

    public QuizRestController(GameService gameService, CommentService commentService, ModelMapper modelMapper) {
        this.gameService = gameService;
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }


//    @GetMapping("/api/games/quest")
//    public ResponseEntity<GameViewModel> getPointUser(Principal principal) {
//
//    //    List<UserViewModel> scoreByUser = gameService.getScoreByUser(principal.getName());
//
//        GameViewModel scoreByUsername = gameService.getScoreByUsername(principal.getName());
//
//        return ResponseEntity.ok(scoreByUsername);
//
//    }
    @GetMapping("/api/games/quest")
    public ResponseEntity<List<GameViewModel>> getPoint(
            Principal principal) {

        List<GameViewModel> all = new ArrayList<>();
        all.add( gameService.getScoreByUser(principal.getName()));

        return ResponseEntity.ok(all);
    }

    @PatchMapping("/api/games/quest")
    public ResponseEntity<GameViewModel> newPoint(
            @AuthenticationPrincipal UserDetails principal ,
            @RequestBody @Valid GamesScoreUserBindingModel gamesScoreUserBindingModel) {

        GameUpdateService gameUpdateService = modelMapper
                .map(gamesScoreUserBindingModel , GameUpdateService.class);

    gameService.updateScore(gameUpdateService);




        return null;


    }



}
