package com.example.arsenalfinalproject.web;

import com.example.arsenalfinalproject.model.view.NewsDetailsView;
import com.example.arsenalfinalproject.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    private final NewsService newsService;

    public HomeController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/")
    public String home(Model model) {


        List<NewsDetailsView> allNews = newsService.findAllNews();

        model.addAttribute("allnews" , allNews);

        return "index";
    }



}
