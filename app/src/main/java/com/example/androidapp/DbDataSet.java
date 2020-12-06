package com.example.androidapp;

public class DbDataSet {

    private String user, passwd, email;
    private int id;

    public DbDataSet(int id, String user, String passwd, String email)
    {
        this.user = user;
        this.passwd = passwd;
        this.email = email;
        this.id = id;
    }

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public String getPasswd()
    {
        return passwd;
    }

    public void setPasswd(String user)
    {
        this.passwd = passwd;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String user)
    {
        this.email = email;
    }

    public int id()
    {
        return id;
    }

    public void setId(String user)
    {
        this.id = id;
    }
}
