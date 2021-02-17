package com.example.androidapp;

public class User {

    private String user;
    private String passwd;
    private String email;
    private int score;
    private int score_percent;

    public User() {
    }

    public User(String user, String passwd, String email, int score, int score_percent) {
        this.user = user;
        this.passwd = passwd;
        this.email = email;
        this.score = score;
        this.score_percent = score_percent;
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

    public int getScore_percent() {
        return score_percent;
    }

    public void setScore_percent(int score_percent) {
        this.score_percent = score_percent;
    }
}
