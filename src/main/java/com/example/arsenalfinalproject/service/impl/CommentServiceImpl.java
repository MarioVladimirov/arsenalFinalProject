package com.example.arsenalfinalproject.service.impl;

import com.example.arsenalfinalproject.model.entity.CommentEntity;
import com.example.arsenalfinalproject.model.entity.NewsEntity;
import com.example.arsenalfinalproject.model.entity.UserEntity;
import com.example.arsenalfinalproject.model.service.CommentServiceModel;
import com.example.arsenalfinalproject.model.view.CommentViewModel;
import com.example.arsenalfinalproject.repository.CommentRepository;
import com.example.arsenalfinalproject.repository.NewsRepository;
import com.example.arsenalfinalproject.repository.UserRepository;
import com.example.arsenalfinalproject.service.CommentService;
import com.example.arsenalfinalproject.service.UserService;
import com.example.arsenalfinalproject.web.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final NewsRepository newsRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, NewsRepository newsRepository, UserService userService, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.newsRepository = newsRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @Override
    public void initializeComments() {

        CommentEntity comment1 = new CommentEntity();
        comment1
                .setApproved(true)
                .setCreated(LocalDateTime.parse("2021-05-03T10:15:30"))
                .setTextContent("comment one ")
                .setAuthor(userService.getUserEntityById(2L))
                .setNews(newsRepository.getById(1L));

        CommentEntity comment2 = new CommentEntity();
        comment2
                .setApproved(true)
                .setCreated(LocalDateTime.parse("2021-05-03T11:15:30"))
                .setTextContent("comment two ")
                .setAuthor(userService.getUserEntityById(1L))
                .setNews(newsRepository.getById(1L));

        commentRepository.save(comment1);
        commentRepository.save(comment2);

    }

    @Override
    public CommentViewModel createComment(CommentServiceModel commentServiceModel) {

        var news = newsRepository.
                findById(commentServiceModel.getRouteId()).
                orElseThrow(() ->
                        new ObjectNotFoundException(commentServiceModel.getRouteId()));

        var author = userRepository.
                findByUsername(commentServiceModel.getCreator()).
                orElseThrow(() ->
                        new ObjectNotFoundException(commentServiceModel.getRouteId()));

        CommentEntity newComment = new CommentEntity();
        newComment.setApproved(false);
        newComment.setTextContent(commentServiceModel.getMessage());
        newComment.setCreated(LocalDateTime.now());
        newComment.setNews(news);
        newComment.setAuthor(author);

        CommentEntity savedComment = commentRepository.save(newComment);


        return mapAsComment(savedComment);
    }

@Transactional
    @Override
    public List<CommentViewModel> getComments(Long newsId) {

        var newsOpt = newsRepository.findById(newsId);

        if (newsOpt.isEmpty()) {
            throw new ObjectNotFoundException(newsId);
        }

        return newsOpt
                .get()
                .getComments()
                .stream()
                .map(this::mapAsComment)
                .collect(Collectors.toList());
    }



    private CommentViewModel mapAsComment(CommentEntity commentEntity) {
        CommentViewModel commentViewModel = new CommentViewModel();

        commentViewModel.
                setCommentId(commentEntity.getId()).
                setCanApprove(true).
                setCanDelete(true).
                setCreated(commentEntity.getCreated()).
                setMessage(commentEntity.getTextContent()).
                setUser(commentEntity.getAuthor().getUsername());

        return commentViewModel;
    }
}
