package com.movieDB.movieDB.model;

import lombok.Builder;
import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Builder
public class Genre {

    @Id
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "genres")
    private List<Movie> movies;

    public Genre() {
    }

    public Genre(Long id, String name, List<Movie> movies) {
        this.id = id;
        this.name = name;
        this.movies = movies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getMovies() {
        return movies.stream()
                .map(Movie::getTitle)
                .collect(Collectors.toList());
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
