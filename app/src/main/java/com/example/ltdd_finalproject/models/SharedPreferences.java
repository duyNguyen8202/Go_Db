package com.example.ltdd_finalproject.models;

import android.content.Context;

public class SharedPreferences {
    private static final String SHARED_PREFS_NAME = "customer_prefs";
    private static final String CUSTOMER_ID_KEY = "customer_id";
    private static final String FULL_NAME_KEY = "full_name";
    private static final String EMAIL_KEY = "email";
    private static final String PHONE_NUMBER_KEY = "phone_number";
    private static final String IMAGE_LINK_KEY = "image_link";
    private static final String ADDRESS_KEY = "address";
    private static final String GENDER_KEY = "gender";
    private static final String BIRTHDAY_KEY = "birthday";
    private static SharedPreferences mInstance;
    private static Context ctx;

    private android.content.SharedPreferences sharedPreferences;

    private SharedPreferences(Context context) {
        ctx = context;
    }

    public static synchronized SharedPreferences getInstance(Context context) {
        if (mInstance == null) {

            mInstance = new SharedPreferences(context);
        }
        return mInstance;
    }

    ////Duy
    // public void saveAccount(Account acc) {

    //     android.content.SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
    //     android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
    //     editor.putString(CUSTOMER_ID_KEY, acc.getAccountId());
    //     editor.putString(FULL_NAME_KEY, acc.getAccountType());
    //     editor.putString(EMAIL_KEY, acc.getCustomerId());
    //     editor.putString(PHONE_NUMBER_KEY, acc.getStaffId());
    //     editor.putString(IMAGE_LINK_KEY, acc.getImageLink());
    //     editor.putString(ADDRESS_KEY, acc.getAddress());
    //     editor.putBoolean(GENDER_KEY, acc.isGender());
    //     editor.apply();
    // }
    ///

    public void saveCustomer(Customer customer) {

        android.content.SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CUSTOMER_ID_KEY, customer.getCustomerId());
        editor.putString(FULL_NAME_KEY, customer.getFullName());
        editor.putString(EMAIL_KEY, customer.getEmail());
        editor.putString(PHONE_NUMBER_KEY, customer.getPhoneNumber());
        editor.putString(IMAGE_LINK_KEY, customer.getImageLink());
        editor.putString(ADDRESS_KEY, customer.getAddress());
        editor.putBoolean(GENDER_KEY, customer.isGender());
        editor.putString(BIRTHDAY_KEY, customer.getBirthDay());
        editor.apply();
    }

    public Customer getCustomer() {
        android.content.SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        String customerId = sharedPreferences.getString(CUSTOMER_ID_KEY, "");
        String fullName = sharedPreferences.getString(FULL_NAME_KEY, "");
        String email = sharedPreferences.getString(EMAIL_KEY, "");
        String phoneNumber = sharedPreferences.getString(PHONE_NUMBER_KEY, "");
        String imageLink = sharedPreferences.getString(IMAGE_LINK_KEY, "");
        String address = sharedPreferences.getString(ADDRESS_KEY, "");
        boolean gender = sharedPreferences.getBoolean(GENDER_KEY, false);
        String birthDay = sharedPreferences.getString(BIRTHDAY_KEY, "");

        return new Customer(customerId, fullName, email, phoneNumber, imageLink, address, gender, birthDay);
    }

    public void clearCustomer() {
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
