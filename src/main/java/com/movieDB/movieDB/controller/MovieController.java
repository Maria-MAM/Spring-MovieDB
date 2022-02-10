package com.movieDB.movieDB.controller;

import com.movieDB.movieDB.exception.GenreNotFoundException;
import com.movieDB.movieDB.exception.MovieNotFoundException;
import com.movieDB.movieDB.model.Movie;
import com.movieDB.movieDB.repositories.GenreRepository;
import com.movieDB.movieDB.repositories.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
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

    @GetMapping(value = "/all")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<Map<String, Object>> getAllMovies(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "3") int size) {

        Pageable paging = PageRequest.of(page, size);
        Page<Movie> pageMovies = movieRepository.findAll(paging);

        return new ResponseEntity<>(populateResponse(pageMovies), HttpStatus.OK);
    }

    @GetMapping(value = "/searchByTitle")
    public ResponseEntity<Map<String, Object>> searchMovieByTitle(@RequestParam("movieTitle") String movieTitle,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "3") int size) {

        Pageable paging = PageRequest.of(page, size);
        Page<Movie> pageMovies = movieRepository.findByTitleContaining(movieTitle, paging);
        return new ResponseEntity<>(populateResponse(pageMovies), HttpStatus.OK);
    }

    @GetMapping(value = "/searchByTitleAndGenre")
    public EntityModel<Map<String, Object>> searchMovieByTitleAndGenre(@RequestParam("movieTitle") String movieTitle,
                                                                           @RequestParam("genre") String genre,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "3") int size) {

        Pageable paging = PageRequest.of(page, size);
        Page<Movie> pageMovies;

        if (genre.isBlank()) {
            pageMovies = movieRepository.findByTitleContaining(movieTitle, paging);
        } else if (genreRepository.findByNameContaining(genre) == null) {
            throw new GenreNotFoundException();
        } else {
            pageMovies = movieRepository
                    .findByTitleContainingAndGenresId(movieTitle, genreRepository.findByNameContaining(genre).getId(), paging);
        }

        EntityModel<Map<String, Object>> resource = EntityModel.of(populateResponse(pageMovies));

        WebMvcLinkBuilder linkToAllMovies = linkTo(methodOn(this.getClass()).getAllMovies(page, size));
        WebMvcLinkBuilder linkToFilterByTitleMovies = linkTo(methodOn(this.getClass()).searchMovieByTitle(movieTitle, page, size));
        WebMvcLinkBuilder linkToSelf = linkTo(methodOn(this.getClass()).searchMovieByTitleAndGenre(movieTitle, genre, page, size));

        resource.add(linkToAllMovies.withRel("all-movies"));
        resource.add(linkToFilterByTitleMovies.withRel("movies-filtered-by-title"));
        resource.add(linkToSelf.withSelfRel());

        return resource;
    }

    private Map<String, Object> populateResponse(Page<Movie> pageMovies) {

        List<Movie> movies = pageMovies.getContent();

        if (movies.size() == 0) throw new MovieNotFoundException();

        Map<String, Object> response = new HashMap<>();
        response.put("movies", movies);
        response.put("currentPage", pageMovies.getNumber());
        response.put("totalItems", pageMovies.getTotalElements());
        response.put("totalPages", pageMovies.getTotalPages());
        return response;
    }
}
