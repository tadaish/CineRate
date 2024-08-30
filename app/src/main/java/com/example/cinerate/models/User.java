package com.example.cinerate.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String username;
    private String password;
    private String role;
    private String created_at;
    private List<Integer> watchlist;


    public User (String username, String password, String role){
        this.username = username;
        this.password = password;
        this.role = role;
        this.watchlist = new ArrayList<>();
    }

    public User(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public void addMovieToWatchlist(int movieId) {
        if (!watchlist.contains(movieId)) {
            watchlist.add(movieId);
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
    public List<Integer> getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(List<Integer> watchlist) {
        this.watchlist = watchlist;
    }

}
