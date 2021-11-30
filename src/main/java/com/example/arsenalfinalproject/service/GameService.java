package com.example.arsenalfinalproject.service;

import com.example.arsenalfinalproject.model.view.GamesScoreUserViewModel;
import com.example.arsenalfinalproject.model.view.UserViewModel;

import java.util.List;

public interface GameService {

    List<UserViewModel> getScoreByUser(String userName);
}
