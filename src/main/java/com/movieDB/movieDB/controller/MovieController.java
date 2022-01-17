package com.movieDB.movieDB.controller;

import com.movieDB.movieDB.model.Movie;
import com.movieDB.movieDB.repositories.GenreRepository;
import com.movieDB.movieDB.repositories.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movies/")
public class MovieController {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    public MovieController(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }

    @GetMapping(value = "/searchByTitle")
    private ResponseEntity<Map<String, Object>> searchMovieByTitle(@RequestParam("movieTitle") String movieTitle,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "3") int size) {

        List<Movie> movies = new ArrayList<Movie>();
        Pageable paging = PageRequest.of(page, size);
        Page<Movie> pageMovies;
        if (movieTitle == null) {
            pageMovies = movieRepository.findAll(paging);
        } else {
            pageMovies = movieRepository.findByTitleContaining(movieTitle, paging);
        }

        movies = pageMovies.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("movies", movies);
        response.put("currentPage", pageMovies.getNumber());
        response.put("totalItems", pageMovies.getTotalElements());
        response.put("totalPages", pageMovies.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/searchByTitleAndGenre")
    private ResponseEntity<Map<String, Object>> searchMovieByTitleAndGenre(@RequestParam("movieTitle") String movieTitle,
                                                                           @RequestParam("genre") String genre,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "3") int size) {

        List<Movie> movies = new ArrayList<Movie>();
        Pageable paging = PageRequest.of(page, size);
        Page<Movie> pageMovies;
        if (movieTitle == null || genre == null || movieTitle.isBlank()) {
            pageMovies = movieRepository.findAll(paging);
        } else if (genre.isBlank()) {
            pageMovies = movieRepository.findByTitleContaining(movieTitle, paging);
        } else {
            pageMovies = movieRepository.findByTitleContainingAndGenresId(movieTitle, genreRepository.findByNameContaining(genre).getId(), paging);
        }

        movies = pageMovies.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("movies", movies);
        response.put("currentPage", pageMovies.getNumber());
        response.put("totalItems", pageMovies.getTotalElements());
        response.put("totalPages", pageMovies.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
