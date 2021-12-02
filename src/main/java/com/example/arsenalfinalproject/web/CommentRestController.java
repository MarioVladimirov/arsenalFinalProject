package com.example.arsenalfinalproject.web;

import com.example.arsenalfinalproject.model.binding.NewCommentBindingModel;
import com.example.arsenalfinalproject.model.service.CommentServiceModel;
import com.example.arsenalfinalproject.model.validator.ApiError;
import com.example.arsenalfinalproject.model.view.CommentViewModel;
import com.example.arsenalfinalproject.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
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

    @PostMapping("/api/{newsId}/comments")
    public ResponseEntity<CommentViewModel> newComment(
            @AuthenticationPrincipal UserDetails principal,
            @PathVariable Long newsId,
            @RequestBody @Valid NewCommentBindingModel newCommentBindingModel
    ) {
        CommentServiceModel serviceModel =
                modelMapper.map(newCommentBindingModel, CommentServiceModel.class);
        serviceModel.setCreator(principal.getUsername());
        serviceModel.setRouteId(newsId);

        CommentViewModel newComment =
                commentService.createComment(serviceModel);

        URI locationOfNewComment =
                URI.create(String.format("/api/%s/comments/%s", newsId, newComment.getCommentId()));

        return ResponseEntity.
                created(locationOfNewComment).
                body(newComment);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> onValidationFailure(MethodArgumentNotValidException exc) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        exc.getFieldErrors().forEach(fe ->
                apiError.addFieldWithError(fe.getField()));

        return ResponseEntity.badRequest().body(apiError);
    }





}
