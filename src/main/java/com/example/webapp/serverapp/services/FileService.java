package com.example.webapp.serverapp.services;

import com.example.webapp.serverapp.models.Trailer;
import com.example.webapp.serverapp.repositories.TrailerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FileService {

    private final TrailerRepository trailerRepository;

    @Autowired
    public FileService(TrailerRepository trailerRepository) {
        this.trailerRepository = trailerRepository;
    }

    public void save(MultipartFile file, String segment_id, String movie_id) throws IOException {
        Trailer trailer = new Trailer();
        trailer.setMovie_id(Long.parseLong(movie_id));
        trailer.setSegment_id(Long.parseLong(segment_id));
        trailer.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        trailer.setContent_type(file.getContentType());
        trailer.setData(file.getBytes());
        trailer.setSize(file.getSize());

        trailerRepository.save(trailer);
    }

    public Optional<Trailer> getFile(Long movie_id, Long segment_id) {
        return trailerRepository.getSegment(movie_id,segment_id);
    }

    public List<Trailer> getAllFiles() {
        return trailerRepository.findAll();
    }

}
