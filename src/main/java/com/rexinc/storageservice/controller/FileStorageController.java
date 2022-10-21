package com.rexinc.storageservice.controller;

import com.rexinc.storageservice.service.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/image_storage")
public class FileStorageController {

    private final StorageService storageService;

    public FileStorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file){
       return new ResponseEntity<>(storageService.fileUpload(file), HttpStatus.OK);
    }

    @GetMapping("/download/{file}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable(name = "file") String filename){
        byte[] imageByte = storageService.downloadImage(filename);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageByte);
    }
}
