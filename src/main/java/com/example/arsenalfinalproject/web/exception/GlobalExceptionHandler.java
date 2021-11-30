package com.example.arsenalfinalproject.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView handlerProductException(ObjectNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("object-not-found");
        modelAndView.addObject("objectId", e.getObjectId());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;

    }



}
