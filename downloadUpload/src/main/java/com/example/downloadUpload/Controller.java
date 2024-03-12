package com.example.downloadUpload;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/1")
public class Controller {
    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file")MultipartFile file){
        return fileService.uploadFile(file);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) {
        byte[] fileContent = fileService.downloadFile(fileName);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + fileName)
                .body(fileContent);
    }
}
