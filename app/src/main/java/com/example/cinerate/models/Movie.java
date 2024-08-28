package com.example.cinerate.models;

public class Movie {
    private int id;
    private  String title;
    private String description;
    private int release_year;
    private String director;
    private String posterUrl;
    private float averageRating;
    private String created_at;
    private String mainCast;
    private String trailerUrl;
    private int languageId;
    private int genreId;

    public Movie(int id, String title, String description, int release_year, String director, String posterUrl, String mainCast, String trailerUrl, int languageId, int genreId){
        this.id = id;
        this.title = title;
        this.description = description;
        this.release_year = release_year;
        this.director = director;
        this.posterUrl = posterUrl;
        this.mainCast = mainCast;
        this.trailerUrl = trailerUrl;
        this.languageId = languageId;
        this.genreId = genreId;
    }

    public Movie( String title, String description, int release_year, String director, String posterUrl, String mainCast, String trailerUrl, int languageId, int genreId){
        this.title = title;
        this.description = description;
        this.release_year = release_year;
        this.director = director;
        this.posterUrl = posterUrl;
        this.mainCast = mainCast;
        this.trailerUrl = trailerUrl;
        this.languageId = languageId;
        this.genreId = genreId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRelease_year() {
        return release_year;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(String createdAt) {
        this.created_at = createdAt;
    }

    public String getMainCast() {
        return mainCast;
    }

    public void setMainCast(String mainCast) {
        this.mainCast = mainCast;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }
}
