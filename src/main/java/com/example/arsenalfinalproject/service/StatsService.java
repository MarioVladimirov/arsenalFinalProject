package com.example.arsenalfinalproject.service;

import com.example.arsenalfinalproject.model.view.StatsView;

public interface StatsService {

    void onRequest();

    StatsView getStats();

    void resetHistory();

}
