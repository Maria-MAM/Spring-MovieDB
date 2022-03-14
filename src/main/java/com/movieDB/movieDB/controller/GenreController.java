package com.movieDB.movieDB.controller;

import com.movieDB.movieDB.services.GenreService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/genres/")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping(value = "/searchByName")
    public ResponseEntity<Map<String, Object>> searchGenreByName(@RequestParam("genreName") String genreName,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "3") int size) {

        Pageable paging = PageRequest.of(page, size);
        return new ResponseEntity<>(genreService.findGenreByName(genreName, paging), HttpStatus.OK);
    }

}
