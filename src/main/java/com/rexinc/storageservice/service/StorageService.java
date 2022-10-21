package com.rexinc.storageservice.service;


import org.springframework.web.multipart.MultipartFile;


public interface StorageService {
    String fileUpload(MultipartFile file);

    byte[] downloadImage(String filename);

}
