package com.movieDB.movieDB.model;

public class GenreAPIResponse {

    private Long id;
    private String name;

    public GenreAPIResponse() {
    }

    public GenreAPIResponse(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "GenreAPIResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
