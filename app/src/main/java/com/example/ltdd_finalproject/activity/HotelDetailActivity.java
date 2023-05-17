package com.example.ltdd_finalproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.models.Hotel;
import com.example.ltdd_finalproject.models.Vehicle;

public class HotelDetailActivity extends AppCompatActivity {
    TextView txthotelId,texthotelName,texthotelAddress,textViewprovince,textViewphoneNumber,textViewemail,textViewwebsite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);
        Intent intent = getIntent();
        Hotel hotel = (Hotel) intent.getSerializableExtra("hotel");

        txthotelId= (TextView) findViewById(R.id.txthotelId);
        texthotelName= (TextView) findViewById(R.id.texthotelName);
        texthotelAddress= (TextView) findViewById(R.id.texthotelAddress);
        textViewprovince= (TextView) findViewById(R.id.textViewprovince);
        textViewphoneNumber= (TextView) findViewById(R.id.textViewphoneNumber);
        textViewemail= (TextView) findViewById(R.id.textViewemail);
        textViewwebsite= (TextView) findViewById(R.id.textViewwebsite);

        txthotelId.setText(hotel.getHotelId());
        texthotelName.setText(hotel.getHotelName());
        texthotelAddress.setText(hotel.getHotelAddress());
        textViewprovince.setText(hotel.getProvince());
        textViewphoneNumber.setText(hotel.getPhoneNumber());
        textViewemail.setText(hotel.getEmail());
        textViewwebsite.setText(hotel.getWebsite());
    }
}