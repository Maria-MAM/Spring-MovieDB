package com.movieDB.movieDB.model;

import java.io.Serializable;
import java.util.List;


public class MovieListAPIResponse implements Serializable {

    private static final long serialVersionUID = 100L;

    private String page;
    private List<MovieAPIResponse> results;
    private int total_pages;
    private int total_results;

    public MovieListAPIResponse() {
    }

    public MovieListAPIResponse(String page, List<MovieAPIResponse> results, int total_pages, int total_results) {
        this.page = page;
        this.results = results;
        this.total_pages = total_pages;
        this.total_results = total_results;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<MovieAPIResponse> getResults() {
        return results;
    }

    public void setResults(List<MovieAPIResponse> results) {
        this.results = results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    @Override
    public String toString() {
        return "MovieListAPIResponse{" +
                "page='" + page + '\'' +
                ", results=" + results +
                ", total_pages=" + total_pages +
                ", total_results=" + total_results +
                '}';
    }
}
