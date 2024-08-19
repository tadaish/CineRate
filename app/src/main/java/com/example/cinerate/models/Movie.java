package com.example.cinerate.models;

public class Movie {
    private int id;
    private  String title;
    private String description;
    private int release_year;
    private String director;
    private int duration;
    private String posterUrl;
    private float averageRating;
    private String createdAt;
    private String mainCast;
    private String trailerUrl;
    private int languageId;
    private String is_active;

    public Movie(int id, String title, String description, int release_year, String director, int duration, String posterUrl,
                 float averageRating, String createdAt, String mainCast, String trailerUrl, int languageId, String is_active){
        this.title = title;
        this.description = description;
        this.release_year = release_year;
        this.director = director;
        this.duration = duration;
        this.posterUrl = posterUrl;
        this.averageRating = averageRating;
        this.createdAt = createdAt;
        this.mainCast = mainCast;
        this.trailerUrl = trailerUrl;
        this.languageId = languageId;
        this.is_active = is_active;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }
}
