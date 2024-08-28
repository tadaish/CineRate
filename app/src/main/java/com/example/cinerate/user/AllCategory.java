package com.example.cinerate.user;

import com.example.cinerate.models.Movie;

import java.util.List;

public class AllCategory {
    String categoryTitle;
    Integer categoryId;

    private List<Movie> moviesList = null;

    public AllCategory(Integer categoryId, String categoryTitle, List<Movie> moviesList) {
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
        this.moviesList = moviesList;
    }

    public List<Movie> getCategoryItemList() {
        return moviesList;
    }

    public void setCategoryItemList(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
