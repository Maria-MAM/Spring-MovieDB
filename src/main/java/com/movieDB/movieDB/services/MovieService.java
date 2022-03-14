package com.movieDB.movieDB.services;

import com.movieDB.movieDB.config.PopulatePageableResponseForRequest;
import com.movieDB.movieDB.exception.GenreNotFoundException;
import com.movieDB.movieDB.model.Movie;
import com.movieDB.movieDB.repositories.GenreRepository;
import com.movieDB.movieDB.repositories.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    private final PopulatePageableResponseForRequest<Movie> populatePageableResponseForRequest =
            new PopulatePageableResponseForRequest<>();

    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }

    public Map<String, Object> getAllMovies(Pageable paging) {
        Page<Movie> pageMovies;
        if (movieRepository.findAll(paging) != null) {
            pageMovies = movieRepository.findAll(paging);
        } else throw new RuntimeException("-------instead of null pointer expception-----");
        return populatePageableResponseForRequest.populateResponse(pageMovies);
    }

    public Map<String, Object> findMovieByTitle(String movieTitle, Pageable paging) {
        Page<Movie> pageMovies = movieRepository.findByTitleContaining(movieTitle, paging);
        return populatePageableResponseForRequest.populateResponse(pageMovies);
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

        return populatePageableResponseForRequest.populateResponse(pageMovies);
    }

}
