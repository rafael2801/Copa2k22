package com.mycompany.copaqatar.models;

public class User {
    private String user_name;
    private Boolean isSuper = false;
    private Boolean isLogged = false;
    private String email;

    public User () {
    }

    public User (String name, Boolean isSuper) {
        this.user_name = name ;
        this.isSuper = isSuper ;
    }

    public Boolean getSuper() {
        return isSuper;
    }

    public void setSuper(Boolean aSuper) {
        isSuper = aSuper;
    }

    public Boolean getLogged() {
        return isLogged;
    }

    public void setLogged(Boolean logged) {
        isLogged = logged;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    private String user_password;


}
