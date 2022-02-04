package com.movieDB.movieDB.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Genre {

    @Id
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "genres")
    @ToString.Exclude
    private List<Movie> movies;

    public List<String> getMovies() {
        return movies.stream()
                .map(Movie::getTitle)
                .collect(Collectors.toList());
    }
}
