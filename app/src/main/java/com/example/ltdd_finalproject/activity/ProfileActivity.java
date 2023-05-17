package com.example.ltdd_finalproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.adapters.ProfileAdapter;

public class ProfileActivity extends AppCompatActivity implements ProfileAdapter.ProfileCallback {
    private TextView customerIdTextView, fullNameTextView, emailTextView, genderTextView,
            birthDayTextView, addressTextView, phoneNumberTextView;
    private ProfileAdapter profileAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        customerIdTextView = findViewById(R.id.customerId);
        fullNameTextView = findViewById(R.id.textViewfullName);
        emailTextView = findViewById(R.id.textViewEmail);
        genderTextView = findViewById(R.id.textViewGender);
        birthDayTextView = findViewById(R.id.textViewbirthDay);
        addressTextView = findViewById(R.id.textViewaddress);
        phoneNumberTextView = findViewById(R.id.textViewphoneNumber);
        profileAdapter = new ProfileAdapter();

        // Replace "username" with the actual username of the logged-in user
        String username = "username";
        getProfile(username);
    }
    private void getProfile(String username) {
        profileAdapter.profile("nhantht0907", this);
    }
    @Override
    public void onProfileSuccess(String message, String customerId, String fullName, String email,
                                 String phoneNumber, String imageLink, String address, boolean gender, String birthDay) {
        // Hiển thị thông tin profile lên các TextView
        customerIdTextView.setText(customerId);
        fullNameTextView.setText(fullName);
        emailTextView.setText(email);
        genderTextView.setText(gender ? "Male" : "Female");
        birthDayTextView.setText(birthDay);
        addressTextView.setText(address);
        phoneNumberTextView.setText(phoneNumber);
    }
    @Override
    public void onProfileFailure(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}