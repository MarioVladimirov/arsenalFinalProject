package com.example.arsenalfinalproject.service;

import com.example.arsenalfinalproject.model.service.CommentServiceModel;
import com.example.arsenalfinalproject.model.view.CommentViewModel;

import java.util.List;

public interface CommentService {

    List<CommentViewModel>  getComments(Long newsId);

    void initializeComments();

    CommentViewModel createComment(CommentServiceModel serviceModel);
}
