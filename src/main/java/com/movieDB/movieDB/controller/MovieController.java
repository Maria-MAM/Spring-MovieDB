package com.movieDB.movieDB.controller;

import com.movieDB.movieDB.model.Movie;
import com.movieDB.movieDB.repositories.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies/")
public class MovieController {

    private MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<Movie>> searchMovie(@RequestParam("movieTitle") String movieTitle) {
        return new ResponseEntity<>(movieRepository.findAllByTitleContaining(movieTitle), HttpStatus.OK);
    }

}
