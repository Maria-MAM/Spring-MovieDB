package com.movieDB.movieDB.services;

import com.movieDB.movieDB.model.Movie;
import com.movieDB.movieDB.repositories.MovieRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;

@Service
public class MovieService {

//    private final String API_KEY = "77832adee6b2234e1c947a27955ac49e";
//
//    private final RestTemplate restTemplate;
//
//    private final MovieRepository movieRepository;
//
//    public MovieService(RestTemplate restTemplate, MovieRepository movieRepository) {
//        this.restTemplate = restTemplate;
//        this.movieRepository = movieRepository;
//    }
//
//    public void populateMovies() {
//        ResponseEntity<List<Movie>> response = restTemplate.exchange(
//                 "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY + "&language=en-" +
//                         "US&query=titanic&page=1&include_adult=false",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Movie>>(){});
//
//        List<Movie> result = response.getBody();
//
//        if(Objects.nonNull(result)) {
//            result.stream().filter(Objects::nonNull)
//                    .forEach(movieRepository::saveAndFlush);
//        }
//
//    }
}
