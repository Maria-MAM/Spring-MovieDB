package com.movieDB.movieDB;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieDB.movieDB.controller.MovieController;
import com.movieDB.movieDB.model.Genre;
import com.movieDB.movieDB.model.Movie;
import com.movieDB.movieDB.repositories.GenreRepository;
import com.movieDB.movieDB.repositories.MovieRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    MovieRepository movieRepository;
    @MockBean
    GenreRepository genreRepository;

    @BeforeEach
    public void setUp() {
        List<Genre> movieGenreList = List.of(Genre.builder().name("Drama").build(),
                Genre.builder().name("Fantasy").build(),
                Genre.builder().name("Action").build());
        Movie movie1 = Movie.builder()
                .id(1L)
                .language("fr")
                .overview("nice movie")
                .title("Movie 1")
                .voteCount(1111)
                .voteAverage(1.1)
                .releaseDate(LocalDate.of(2000, 1, 1))
                .popularity(11L)
                .genres(movieGenreList)
                .build();
        Movie movie2 = Movie.builder()
                .id(2L)
                .language("fr")
                .overview("very nice movie")
                .title("Movie 2")
                .voteCount(2222)
                .voteAverage(2.2)
                .releaseDate(LocalDate.of(2000, 2, 2))
                .popularity(22L)
                .genres(movieGenreList)
                .build();
        List<Movie> movies = new ArrayList<>(List.of(movie1, movie2));

        Movie moviesaved1 = movieRepository.saveAndFlush(movie1);
        Movie moviesaved2 = movieRepository.saveAndFlush(movie2);

        System.out.println("----------------" + movie1);
        System.out.println("----------------" + movie2);
        System.out.println("----------------" + moviesaved1 );
        System.out.println("----------------" + moviesaved2 );
        System.out.println("----------------" + movieRepository);
        System.out.println("----------------" + movieRepository.findAll());
    }

    @Test
    public void getAllMovies_success() throws Exception {

        Page tasks = Mockito.mock(Page.class);
        Mockito.when(this.movieRepository.findAll(org.mockito.ArgumentMatchers.isA(Pageable.class))).thenReturn(tasks);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/movies/all")
                        .header("Authorization", "Basic " + Base64Utils.encodeToString("john.doe:secret".getBytes()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].title", is("Movie 2")))
        ;
    }

}
