package com.example.ltdd_finalproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.models.Hotel;
import com.example.ltdd_finalproject.models.Vehicle;

public class HotelDetailActivity extends AppCompatActivity {
    TextView txthotelId,texthotelName,texthotelAddress,textViewprovince,textViewphoneNumber,textViewemail,textViewwebsite;
    String customerid = new String();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);
        Intent intent = getIntent();
        Hotel hotel = (Hotel) intent.getSerializableExtra("hotel");
        customerid = getIntent().getStringExtra("customerid");
        if(customerid !=null)
        {
            Toast.makeText(this, "ID:"+customerid, Toast.LENGTH_SHORT).show();
        }
        txthotelId= findViewById(R.id.txthotelId);
        texthotelName= findViewById(R.id.texthotelName);
        texthotelAddress= findViewById(R.id.texthotelAddress);
        textViewprovince= findViewById(R.id.textViewprovince);
        textViewphoneNumber= findViewById(R.id.textViewphoneNumber);
        textViewemail= findViewById(R.id.textViewemail);
        textViewwebsite= findViewById(R.id.textViewwebsite);

        txthotelId.setText(hotel.getHotelId());
        texthotelName.setText(hotel.getHotelName());
        texthotelAddress.setText(hotel.getHotelAddress());
        textViewprovince.setText(hotel.getProvince());
        textViewphoneNumber.setText(hotel.getPhoneNumber());
        textViewemail.setText(hotel.getEmail());
        textViewwebsite.setText(hotel.getWebsite());
    }
}