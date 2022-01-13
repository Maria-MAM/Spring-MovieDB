package com.movieDB.movieDB.model;

import java.util.List;

public class GenreListAPIResponse {

    private List<GenreAPIResponse> genres;

    public GenreListAPIResponse() {
    }

    public GenreListAPIResponse(List<GenreAPIResponse> genres) {
        this.genres = genres;
    }

    public List<GenreAPIResponse> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreAPIResponse> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "GenreListAPIResponse{" +
                "genres=" + genres +
                '}';
    }
}
