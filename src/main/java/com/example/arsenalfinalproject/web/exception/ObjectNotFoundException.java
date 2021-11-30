package com.example.arsenalfinalproject.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException{

    public  final Long objectId;

    public ObjectNotFoundException(Long objectId) {
            super("Object wit id " + objectId + " not found!");
            this.objectId = objectId;
    }

    public Long getObjectId() {
        return objectId;
    }
}
