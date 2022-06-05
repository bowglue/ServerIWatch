package com.example.webapp.serverapp.controllers;

import com.example.webapp.serverapp.models.Trailer;
import com.example.webapp.serverapp.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.List;
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

    interface TrailerInterface{
        Long getMovie_id();
        Long getSegment_id();
    }

    @GetMapping("/segment")
    public ResponseEntity<byte[]> getFile(@RequestParam("data") Long[] trailer) {
        Optional<Trailer> fileEntityOptional = fileService.getFile(trailer[0], trailer[1]);

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
