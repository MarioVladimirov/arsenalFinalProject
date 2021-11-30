package com.example.arsenalfinalproject.web;

import com.example.arsenalfinalproject.model.view.CommentViewModel;
import com.example.arsenalfinalproject.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class CommentRestController {


    private final CommentService commentService;
    private final ModelMapper modelMapper;

    public CommentRestController(CommentService commentService, ModelMapper modelMapper) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api/{newsId}/comments")
    public ResponseEntity<List<CommentViewModel>> getComments(
            @PathVariable Long newsId,
            Principal principal
    ) {
        return ResponseEntity.ok(
                commentService.getComments(newsId));
    }




}
