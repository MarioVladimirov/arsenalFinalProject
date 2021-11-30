package com.example.arsenalfinalproject.service;

import com.example.arsenalfinalproject.model.entity.PictureEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface PictureService {
    PictureEntity createPictureEntity(MultipartFile picture) throws IOException;

    PictureEntity createPictureEntityByPathInit(String path) throws IOException;

    void deletePictureByPublicId(String publicId);
}
