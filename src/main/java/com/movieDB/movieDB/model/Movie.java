package com.movieDB.movieDB.model;

import lombok.Builder;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Builder
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    private String language;
    private String title;

    private double voteAverage;
    private int voteCount;

    @Column(length = 3000)
    private String overview;

    @ManyToMany
    @JoinTable(
            name = "movies_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    private Date releaseDate;
    private Integer popularity;

    public Movie() {
    }

    public Movie(Long id, String language, String title, double voteAverage, int voteCount,
                 String overview, List<Genre> genres, Date releaseDate, Integer popularity) {
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

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
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
                ", genres=" + genres +
                ", releaseDate=" + releaseDate +
                ", popularity=" + popularity +
                '}';
    }
}
