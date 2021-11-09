package com.example.arsenalfinalproject.service;

import com.example.arsenalfinalproject.model.view.NewsViewModel;

import java.util.List;

public interface NewsService {


    List<NewsViewModel> findAllNews();

    void initializeNews();
}
