package com.example.myapplication.model;

public class User {
    private int id;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userDob;
    private String userRadioButton;

    public User() {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userDob = userDob;
        this.userRadioButton = userRadioButton;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserDob() {
        return userDob;
    }

    public void setUserDob(String userDob) {
        this.userDob = userDob;
    }

    public String getUserRadioButton() {
        return userRadioButton;
    }

    public void setUserRadioButton(String userRadioButton) {
        this.userRadioButton = userRadioButton;
    }
}
