package com.example.webapp.serverapp.controllers;

import com.example.webapp.serverapp.models.Movie;
import com.example.webapp.serverapp.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/test")
    @Transactional
    public List<Movie> movie(){
        return movieRepository.findAll();
    }

    @GetMapping("/image")
    @CrossOrigin(origins = "https://frostwatch.herokuapp.com")
    @Transactional
    public List<MovieRepository.MovieInterface> listImage(@RequestParam("page") int page, @RequestParam("numberMovie") int numberMovie) {
        Pageable number = PageRequest.of(page, numberMovie, Sort.by("movie_id"));
        return movieRepository.findAllMovieImage(number);
    }

    @GetMapping("/focus/{id}")
    @CrossOrigin(origins = "https://frostwatch.herokuapp.com")
    @Transactional
    public MovieRepository.MovieInterface focusImage(@PathVariable Long id) {
        return movieRepository.findFocusImage(id);
    }

    @GetMapping("/header")
    @CrossOrigin(origins = "https://frostwatch.herokuapp.com")
    @Transactional
    public List<MovieRepository.MovieInterface> headerImage(@RequestParam("page") int page, @RequestParam("numberMovie") int numberMovie) {
        Pageable number = PageRequest.of(page, numberMovie, Sort.by("movie_id"));
        return movieRepository.findHeaderImage(number);
    }


   /* @RequestMapping("{id}")
    @Transactional
    public MovieRepository.MovieInterface trailer(@PathVariable("id") Long id) throws MalformedURLException {
        //MovieRepository.MovieInterface movieTrailer = movieRepository.findMovieTrailerById(id);
        return  movieRepository.findMovieTrailerById(id);
    }*/

    @PostMapping("/upload")
    @CrossOrigin(origins = "https://frostwatch.herokuapp.com")
    public Movie uploadImage(@RequestBody final Movie movie) {
        return movieRepository.saveAndFlush(movie);
    }


    /*@PostMapping("/upload")
    public ResponseEntity uploadImage(@RequestParam("image") MultipartFile file) throws IOException {

        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        Movie img = new Movie(file.getOriginalFilename(),
                compressImage(file.getBytes()));
        movieRepository.save(img);
        return ResponseEntity.ok("200");
    }*/

    /*  @MessageMapping("/trailer")
      @SendTo("/topic/movie")
      @Transactional
      public MovieRepository.MovieInterface  message(Long id) throws Exception {
          return  movieRepository.findMovieTrailerById(id);
      }
  */
    public static byte[] compressImage(byte[] data) throws IOException {

        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }


    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
