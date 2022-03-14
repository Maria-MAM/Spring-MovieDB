package com.movieDB.movieDB.services;

import com.movieDB.movieDB.exception.GenreNotFoundException;
import com.movieDB.movieDB.model.Genre;
import com.movieDB.movieDB.repositories.GenreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Map<String, Object> findGenreByName(String genreName, Pageable paging) {
        Page<Genre> pageGenre = genreRepository.findByNameContaining(genreName, paging);
        return populateResponse(pageGenre);
    }

    private Map<String, Object> populateResponse(Page<Genre> page) {
        List<Genre> items = page.getContent();
        if (items.size() == 0) {
            throw new GenreNotFoundException();
        }
        Map<String, Object> response = new HashMap<>();
        response.put("genres", items);
        response.put("currentPage", page.getNumber());
        response.put("totalItems", page.getTotalElements());
        response.put("totalPages", page.getTotalPages());
        return response;
    }
}
