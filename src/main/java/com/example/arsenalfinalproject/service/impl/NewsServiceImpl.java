package com.example.arsenalfinalproject.service.impl;

import com.example.arsenalfinalproject.model.view.NewsViewModel;
import com.example.arsenalfinalproject.repository.NewsRepository;
import com.example.arsenalfinalproject.service.NewsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final ModelMapper modelMapper;

    public NewsServiceImpl(NewsRepository newsRepository, ModelMapper modelMapper) {
        this.newsRepository = newsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<NewsViewModel> findAllNews() {
        return newsRepository
                .findAll()
                .stream()
                .map(news -> {
                    NewsViewModel newsViewModel = modelMapper.map(news , NewsViewModel.class);

                    return newsViewModel;
                })
                .collect(Collectors.toList());
    }
}
