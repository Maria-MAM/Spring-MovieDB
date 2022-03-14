package com.movieDB.movieDB.controller;

import com.movieDB.movieDB.services.MovieService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Map;

@RestController
@RequestMapping("/movies/")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(value = "/all")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<Map<String, Object>> getAllMovies(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "3") int size) {

        Pageable paging = PageRequest.of(page, size);
        return new ResponseEntity<>(movieService.getAllMovies(paging), HttpStatus.OK);
    }

    @GetMapping(value = "/searchByTitle")
    public ResponseEntity<Map<String, Object>> searchMovieByTitle(@RequestParam("movieTitle") String movieTitle,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "3") int size) {

        Pageable paging = PageRequest.of(page, size);
        return new ResponseEntity<>(movieService.findMovieByTitle(movieTitle, paging), HttpStatus.OK);
    }

    @GetMapping(value = "/searchByTitleAndGenre")
    public EntityModel<Map<String, Object>> searchMovieByTitleAndGenre(@RequestParam("movieTitle") String movieTitle,
                                                                       @RequestParam("genre") String genre,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "3") int size) {

        Pageable paging = PageRequest.of(page, size);

        EntityModel<Map<String, Object>> resource =
                EntityModel.of(movieService.findMovieByTitleAndGenre(movieTitle, genre, paging));

        WebMvcLinkBuilder linkToAllMovies = linkTo(methodOn(this.getClass()).getAllMovies(page, size));
        WebMvcLinkBuilder linkToFilterByTitleMovies = linkTo(methodOn(this.getClass()).searchMovieByTitle(movieTitle, page, size));
        WebMvcLinkBuilder linkToSelf = linkTo(methodOn(this.getClass()).searchMovieByTitleAndGenre(movieTitle, genre, page, size));

        resource.add(linkToAllMovies.withRel("all-movies"));
        resource.add(linkToFilterByTitleMovies.withRel("movies-filtered-by-title"));
        resource.add(linkToSelf.withSelfRel());

        return resource;
    }

}
