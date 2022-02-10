package com.movieDB.movieDB;

import com.movieDB.movieDB.model.Genre;
import com.movieDB.movieDB.model.Movie;
import com.movieDB.movieDB.repositories.MovieRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MovieRepositoryTest {
    @Autowired
    MovieRepository repository;

    @Test
    public void testRepository() {
        List<Genre> movieGenreList = List.of(Genre.builder().name("Drama").build(),
                Genre.builder().name("Fantasy").build(),
                Genre.builder().name("Action").build());
        Movie movie1 = Movie.builder()
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

        repository.save(movie1);

        Assertions.assertNotNull(movie1.getId());
    }
}