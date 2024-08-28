package com.example.cinerate.user;

public class CategoryItem {
    Integer id;
    String title;
    String posterUrl;
    String trailerUrl;

    public CategoryItem(Integer id, String movieName, String posterUrl, String trailerUrl) {
        this.id = id;
        this.title = movieName;
        this.posterUrl = posterUrl;
        this.trailerUrl = trailerUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }
}
