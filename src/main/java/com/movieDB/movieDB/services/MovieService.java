package com.movieDB.movieDB.services;

import com.movieDB.movieDB.exception.GenreNotFoundException;
import com.movieDB.movieDB.exception.MovieNotFoundException;
import com.movieDB.movieDB.model.Movie;
import com.movieDB.movieDB.repositories.GenreRepository;
import com.movieDB.movieDB.repositories.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }

    public Map<String, Object> getAllMovies(Pageable paging) {
        Page<Movie> pageMovies;
        if (movieRepository.findAll(paging) != null) {
            pageMovies = movieRepository.findAll(paging);
        } else throw new RuntimeException("-------instead of null pointer expception-----");
        return populateResponse(pageMovies);
    }

    public Map<String, Object> findMovieByTitle(String movieTitle, Pageable paging) {
        Page<Movie> pageMovies = movieRepository.findByTitleContaining(movieTitle, paging);
        return populateResponse(pageMovies);
    }

    public Map<String, Object> findMovieByTitleAndGenre(String movieTitle, String genre, Pageable paging) {

        Page<Movie> pageMovies;
        if (genre.isBlank()) {
            pageMovies = movieRepository.findByTitleContaining(movieTitle, paging);
        } else if (genreRepository.findByNameContaining(genre) == null) {
            throw new GenreNotFoundException();
        } else {
            pageMovies = movieRepository
                    .findByTitleContainingAndGenresId(movieTitle, genreRepository.findByNameContaining(genre).getId(), paging);
        }
        return populateResponse(pageMovies);
    }

    private Map<String, Object> populateResponse(Page<Movie> page) {
        List<Movie> items = page.getContent();
        if (items.size() == 0) {
            throw new MovieNotFoundException();
            }
        Map<String, Object> response = new HashMap<>();
        response.put("movies", items);
        response.put("currentPage", page.getNumber());
        response.put("totalItems", page.getTotalElements());
        response.put("totalPages", page.getTotalPages());
        return response;
    }


}
