package com.example.cinerate.models;

public class Comment {
    private int id;
    private int movie_id;
    private int user_id;
    private String content;
    private String created_at;

    public Comment(int id, int movie_id, int user_id, String content, String created_at) {
        this.id = id;
        this.movie_id = movie_id;
        this.user_id = user_id;
        this.content = content;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
