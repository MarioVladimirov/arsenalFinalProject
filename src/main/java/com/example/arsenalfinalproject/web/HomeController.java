package com.example.arsenalfinalproject.web;

import com.example.arsenalfinalproject.model.view.NewsDetailsView;
import com.example.arsenalfinalproject.service.NewsService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Controller
public class HomeController {

    private final NewsService newsService;

    public HomeController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/")
    public String home(Model model) {


//        List<NewsDetailsView> allNews = newsService.findAllNews();
//
//        model.addAttribute("allnews" , allNews);

        return findPaginated(1, model);

    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 2;

        Page<NewsDetailsView> page = newsService.findPaginated(pageNo, pageSize);
        List<NewsDetailsView> allNews = page.getContent();


        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("allNews", allNews);

        return "index";

    }


}
