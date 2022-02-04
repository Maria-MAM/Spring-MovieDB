package com.movieDB.movieDB.model;

import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    private String language;
    private String title;
    private Double voteAverage;
    private Integer voteCount;

    @Nationalized
    @Column(length = 1000)
    private String overview;

    @ManyToMany
    @JoinTable(
            name = "movies_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @ToString.Exclude
    private List<Genre> genres;

    private LocalDate releaseDate;
    private Long popularity;

    public List<String> getGenres() {
        return genres.stream()
                .map(Genre::getName)
                .collect(Collectors.toList());
    }
}
