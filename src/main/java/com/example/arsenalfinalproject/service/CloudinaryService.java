package com.example.arsenalfinalproject.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface CloudinaryService {

    CloudinaryImage upload(MultipartFile file) throws IOException;

    boolean delete(String publicId);


    CloudinaryImage uploadInit(String path) throws IOException;
}
