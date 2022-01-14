package com.movieDB.movieDB.repositories;

import com.movieDB.movieDB.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAllByTitleContaining(String title);
}
