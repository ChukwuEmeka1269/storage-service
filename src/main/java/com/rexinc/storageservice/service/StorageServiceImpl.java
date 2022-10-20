package com.rexinc.storageservice.service;

import com.rexinc.storageservice.exception.ImageDoesNotExistException;
import com.rexinc.storageservice.model.entity.FileData;
import com.rexinc.storageservice.repository.FileDataRepository;
import com.rexinc.storageservice.util.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.util.Optional;

public class StorageServiceImpl implements StorageService {

    private final Logger logger = LoggerFactory.getLogger(StorageServiceImpl.class);
    private final FileDataRepository fileDataRepository;

    public StorageServiceImpl(FileDataRepository fileDataRepository) {
        this.fileDataRepository = fileDataRepository;
    }


    @Override
    public String fileUpload(MultipartFile file) {
        String randomAlphaNumericString = RandomStringUtils.randomAlphanumeric(16);
        logger.info("Random Reference: " + randomAlphaNumericString);

        try {
            fileDataRepository.save(FileData.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .imageReference(randomAlphaNumericString)
                    .imageData(ImageUtils.compressImage(file.getBytes()))
                    .build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.info(String.format("File %s saved successfully", file.getOriginalFilename()));
        return randomAlphaNumericString;
    }

    @Override
    public byte[] downloadImage(String filename) {
        FileData dbImageData = fileDataRepository.findByName(filename)
                .orElseThrow(() -> new ImageDoesNotExistException(String.format("Image with name %s does not exist", filename)));
        return ImageUtils.decompressImage(dbImageData.getImageData());
    }


}
