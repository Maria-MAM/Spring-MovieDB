package com.movieDB.movieDB.model;

import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Builder
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    private String language;
    private String title;
    private Double voteAverage;
    private Integer voteCount;

    @Column(length = 3000)
    private String overview;

    @ManyToMany
    @JoinTable(
            name = "movies_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    private LocalDate releaseDate;
    private Long popularity;

    public Movie() {
    }

    public Movie(Long id, String language, String title, Double voteAverage, Integer voteCount,
                 String overview, List<Genre> genres, LocalDate releaseDate, Long popularity) {
        this.id = id;
        this.language = language;
        this.title = title;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.overview = overview;
        this.genres = genres;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
    }

    public Movie(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public List<String> getGenres() {
        return genres.stream()
                .map(Genre::getName)
                .collect(Collectors.toList());
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Long getPopularity() {
        return popularity;
    }

    public void setPopularity(Long popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", language='" + language + '\'' +
                ", title='" + title + '\'' +
                ", voteAverage=" + voteAverage +
                ", voteCount=" + voteCount +
                ", overview='" + overview + '\'' +
                ", releaseDate=" + releaseDate +
                ", popularity=" + popularity +
                '}';
    }
}
