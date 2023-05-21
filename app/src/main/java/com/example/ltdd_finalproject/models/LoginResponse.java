package com.example.ltdd_finalproject.models;

public class LoginResponse {
    private boolean success;
    private String message;
    private String account_type;

    public String getUsername() {
        return username;
    }

    private String username;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getAccount_type() {
        return account_type;
    }
}
