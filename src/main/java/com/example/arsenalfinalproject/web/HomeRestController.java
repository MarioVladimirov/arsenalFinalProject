package com.example.arsenalfinalproject.web;

import com.example.arsenalfinalproject.model.view.NewsDetailsView;
import com.example.arsenalfinalproject.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeRestController {

    private final NewsService newsService;

    public HomeRestController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/api/allnews")
    public ResponseEntity<List<NewsDetailsView>> getAllNews() {

        List<NewsDetailsView> allNews = newsService.findAllNews();

        return ResponseEntity
                .ok(allNews);
    }



}
