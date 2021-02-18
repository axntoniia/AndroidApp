package com.example.androidapp;

public class User {

    private String user;
    private String passwd;
    private String email;
    private int score;
    private int score_total;

    public User() {
    }

    public User(String user, String passwd, String email, int score, int score_total) {
        this.user = user;
        this.passwd = passwd;
        this.email = email;
        this.score = score;
        this.score_total = score_total;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore_total() {
        return score_total;
    }

    public void setScore_total(int score_total) {
        this.score_total = score_total;
    }
}
