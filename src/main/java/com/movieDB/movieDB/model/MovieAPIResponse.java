package com.movieDB.movieDB.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonDeserialize(using = MovieAPIResponseDeserializer.class)
public class MovieAPIResponse implements Serializable {

    private static final long serialVersionUID = 100L;

    private Long id;
    private List<Long> genre_ids;
    private String original_language;

    @Column(length = 3000)
    private String overview;
    private Long popularity;
    private Date release_date;
    private String title;

    @JsonProperty("vote-average")
    private double voteAverage;

    @JsonProperty("vote-count")
    private int voteCount;

    public MovieAPIResponse() {
    }

    public MovieAPIResponse(  Long popularity, double voteAverage, int voteCount) {
        this.popularity = popularity;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Long> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Long getPopularity() {
        return popularity;
    }

    public void setPopularity(Long popularity) {
        this.popularity = popularity;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
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

    @Override
    public String toString() {
        return "MovieAPIResponse{" +
                "id=" + id +
                ", genre_ids=" + genre_ids +
                ", original_language='" + original_language + '\'' +
                ", overview='" + overview + '\'' +
                ", popularity=" + popularity +
                ", release_date=" + release_date +
                ", title='" + title + '\'' +
                ", voteAverage=" + voteAverage +
                ", voteCount=" + voteCount +
                '}';
    }
}

