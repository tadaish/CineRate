package com.example.cinerate.models;

public class User {
    private int id;
    private String username;
    private String password;
    private String created_at;

    public User(int id, String username, String password, String created_at) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.created_at = created_at;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
