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
    private final CommentService commentService;

    public DBInit(MemberTopicService memberTopicService, ProductService productService, UserService userService, NewsService newsService, CommentService commentService) {
        this.memberTopicService = memberTopicService;
        this.productService = productService;
        this.userService = userService;
        this.newsService = newsService;
        this.commentService = commentService;
    }


    @Override
    public void run(String... args) throws Exception {
        productService.initializeProduct();
        userService.initializeUsersAndRoles();
        newsService.initializeNews();
        memberTopicService.initializeTopicMember();
        commentService.initializeComments();
    }
}
