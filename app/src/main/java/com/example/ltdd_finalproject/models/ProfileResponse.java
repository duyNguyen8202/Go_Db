package com.example.ltdd_finalproject.models;

public class ProfileResponse {
    private boolean success;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getImage_link() {
        return image_link;
    }

    public String getAddress() {
        return address;
    }

    public boolean isGender() {
        return gender;
    }

    public String getBirthday() {
        return birthday;
    }

    private String customer_id;
    private String full_name;
    private String email;
    private String phone_number;
    private String image_link;
    private String address;
    private boolean gender;
    private String birthday;

}
