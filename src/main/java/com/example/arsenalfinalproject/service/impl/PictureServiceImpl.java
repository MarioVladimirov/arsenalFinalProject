package com.example.arsenalfinalproject.service.impl;

import com.example.arsenalfinalproject.model.entity.PictureEntity;
import com.example.arsenalfinalproject.repository.PictureRepository;
import com.example.arsenalfinalproject.service.CloudinaryImage;
import com.example.arsenalfinalproject.service.CloudinaryService;
import com.example.arsenalfinalproject.service.PictureService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class PictureServiceImpl implements PictureService {

    private final CloudinaryService cloudinaryService;
    private final PictureRepository pictureRepository;

    public PictureServiceImpl(CloudinaryService cloudinaryService, PictureRepository pictureRepository) {
        this.cloudinaryService = cloudinaryService;
        this.pictureRepository = pictureRepository;
    }


    @Override
    public PictureEntity createPictureEntity(MultipartFile file) throws IOException {
        final CloudinaryImage uploaded = cloudinaryService.upload(file);

        PictureEntity pictureEntity = new PictureEntity();
            pictureEntity
                    .setPublicId(uploaded.getPublicId())
                    .setUrl(uploaded.getUrl());

        pictureRepository.save(pictureEntity);

        return pictureEntity;

    }

    @Override
    public PictureEntity createPictureEntityByPathInit(String path) throws IOException {
        final CloudinaryImage uploaded = cloudinaryService.uploadInit(path);



        PictureEntity pictureEntity = new PictureEntity();
        pictureEntity
                .setPublicId(uploaded.getPublicId())
                .setUrl(uploaded.getUrl());
        pictureRepository.save(pictureEntity);

        return pictureEntity;

    }

    @Override
    public void deletePictureByPublicId(String publicId) {

        Long id = pictureRepository.findByPublicId(publicId).getId();
        pictureRepository.deleteById(id);

    }


}
