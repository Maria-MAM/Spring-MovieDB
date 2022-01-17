package com.movieDB.movieDB.repositories;

import com.movieDB.movieDB.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {

    Page<Movie> findByTitleContaining(String title, Pageable pageable);
    Page<Movie> findByTitleContainingAndGenresId(String title, Long id, Pageable pageable);

}
