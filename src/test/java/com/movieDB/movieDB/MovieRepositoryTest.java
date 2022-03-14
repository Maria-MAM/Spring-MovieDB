package com.movieDB.movieDB;

import com.movieDB.movieDB.model.Genre;
import com.movieDB.movieDB.model.Movie;
import com.movieDB.movieDB.repositories.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class MovieRepositoryTest {
    @Autowired
    private MovieRepository repository;

    @Test
    public void itShouldFindByTitleContaining() {
        String title = "Movie 2";
        List<Genre> movieGenreList = List.of(Genre.builder().id(11L).name("Drama").build(),
                Genre.builder().id(22L).name("Fantasy").build());
        Movie movie1 = Movie.builder()
                .id(2L)
                .language("fr")
                .overview("very nice movie")
                .title(title)
                .voteCount(2222)
                .voteAverage(2.2)
                .releaseDate(LocalDate.of(2000, 2, 2))
                .popularity(22L)
                .genres(movieGenreList)
                .build();
        Pageable paging = PageRequest.of(0, 3);
        repository.save(movie1);

        Page<Movie> moviePage = repository.findByTitleContaining(title, paging);

        assertEquals(title, moviePage.getContent().get(0).getTitle());
    }
}