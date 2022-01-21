package com.movieDB.movieDB.repositories;

import com.movieDB.movieDB.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    Page<Genre> findByNameContaining(String name, Pageable pageable);
    Genre findByNameContaining(String name);
}
