package com.movieDB.movieDB.controller;

import com.movieDB.movieDB.exception.GenreNotFoundException;
import com.movieDB.movieDB.exception.MovieNotFoundException;
import com.movieDB.movieDB.model.Genre;
import com.movieDB.movieDB.repositories.GenreRepository;
import com.movieDB.movieDB.repositories.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/genres/")
public class GenreController {

    private final GenreRepository genreRepository;

    public GenreController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @GetMapping(value = "/searchByName")
    public ResponseEntity<Map<String, Object>> searchGenreByName(@RequestParam("genreName") String genreName,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "3") int size) {

        Pageable paging = PageRequest.of(page, size);
        Page<Genre> pageGenre = genreRepository.findByNameContaining(genreName, paging);

        return new ResponseEntity<>(populateResponse(pageGenre), HttpStatus.OK);
    }

    private Map<String, Object> populateResponse(Page<Genre> pageGenre) {
        List<Genre> genres =  pageGenre.getContent();

        if (genres.size() == 0) throw new GenreNotFoundException();

        Map<String, Object> response = new HashMap<>();
        response.put("genres", genres);
        response.put("currentPage", pageGenre.getNumber());
        response.put("totalItems", pageGenre.getTotalElements());
        response.put("totalPages", pageGenre.getTotalPages());
        return response;
    }
}
