package com.example.downloadUpload;

import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    @Value("${upload.folder.path}")
    private String uploadPercorso;

    @Value("${download.folder.path")
    private String downloadPercorso;

    public String uploadFile(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            String filePath = uploadPercorso + "/" + file.getOriginalFilename();
            Path path = Paths.get(filePath);
            Files.write(path, bytes);
            return file.getOriginalFilename();
        } catch (IOException e) {
            throw new RuntimeException("Upload fallito");
        }
    }

    public byte[] downloadFile(String fileName) {
        try {
            String filePath = downloadPercorso + "/" + fileName;
            Path path = Paths.get(filePath);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException("Download Fallito");
        }
    }
}
