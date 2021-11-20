package com.example.arsenalfinalproject.init;

import com.example.arsenalfinalproject.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

    private final MemberTopicService memberTopicService;
    private final ProductService productService;
    private final UserService userService;
    private final NewsService newsService;


    public DBInit(MemberTopicService memberTopicService, ProductService productService, UserService userService, NewsService newsService) {
        this.memberTopicService = memberTopicService;
        this.productService = productService;
        this.userService = userService;
        this.newsService = newsService;

    }


    @Override
    public void run(String... args) throws Exception {
        memberTopicService.initializeTopicMember();
        productService.initializeProduct();
        userService.initializeUsersAndRoles();
        newsService.initializeNews();


    }
}
