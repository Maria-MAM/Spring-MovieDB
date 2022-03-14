package com.movieDB.movieDB.services;

import com.movieDB.movieDB.config.PopulatePageableResponseForRequest;
import com.movieDB.movieDB.model.Genre;
import com.movieDB.movieDB.repositories.GenreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    private final PopulatePageableResponseForRequest<Genre> populatePageableResponseForRequest =
            new PopulatePageableResponseForRequest<>();

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Map<String, Object> findGenreByName(String genreName, Pageable paging) {

        Page<Genre> pageGenre = genreRepository.findByNameContaining(genreName, paging);

        return populatePageableResponseForRequest.populateResponse(pageGenre);
    }
}
