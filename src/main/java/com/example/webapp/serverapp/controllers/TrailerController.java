package com.example.webapp.serverapp.controllers;

import com.example.webapp.serverapp.models.Trailer;
import com.example.webapp.serverapp.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/trailer")
public class TrailerController {

    private final FileService fileService;

    @Autowired
    public TrailerController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public ResponseEntity<String> upload(@RequestParam("data") MultipartFile file, @RequestParam("segment_id") String segment_id, @RequestParam("movie_id") String movie_id) {
        try {
            fileService.save(file, segment_id, movie_id);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }

    @GetMapping("/segment")
    public ResponseEntity<byte[]> getFile(@RequestParam("movieId") Long movieId, @RequestParam("segmentId") Long segmentId) {
        Optional<Trailer> fileEntityOptional = fileService.getFile(movieId, segmentId);

        if (!fileEntityOptional.isPresent()) {
            return ResponseEntity.notFound()
                    .build();
        }

        Trailer fileEntity = fileEntityOptional.get();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")
                .contentType(MediaType.valueOf(fileEntity.getContent_type()))
                .body(fileEntity.getData());
    }


}
