package com.example.ltdd_finalproject.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.models.AddTourResponse;
import com.example.ltdd_finalproject.models.Hotel;
import com.example.ltdd_finalproject.models.SharedPreferences;
import com.example.ltdd_finalproject.retro.API;
import com.example.ltdd_finalproject.retro.RetrofitClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailHotelActivity extends AppCompatActivity {
    TextView txthotelId,texthotelName,texthotelAddress,textViewprovince,textViewphoneNumber,textViewemail,textViewwebsite;
    Button btnBook;
    private API hotelApi;

    Intent intent ;
    String username;
    Hotel hotel;
    LocalDate currentDate = LocalDate.now();
    String date = currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    String customerID = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);
        intent = getIntent();
        if (intent != null) {
        hotel = (Hotel) intent.getSerializableExtra("hotel");
        username= intent.getStringExtra("username");
        Log.d("AllHotel",username);
        } else {
            finish();
        }
        // Khởi tạo đối tượng CustomerSharedPreferences
        customerID = SharedPreferences.getInstance(this).getCustomer().getCustomerId();
        anhxa();
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailHotelActivity.this);
                builder.setTitle("Confirm Booking");
                builder.setMessage("Are you sure you want to book this hotel?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addHotel();
                        Intent intent = new Intent(DetailHotelActivity.this, HomeActivity.class);
                        intent.putExtra("hotel", hotel);
                        intent.putExtra("username",username);
                        intent.putExtra("fragment", "booking_fragment");
                        Toast.makeText(getApplicationContext(), "Booking confirmed", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void addHotel() {
        hotelApi = RetrofitClient.getRetrofit().create(API.class);
        Call<AddTourResponse> call = hotelApi.addHotel(customerID, hotel.getHotelId(), date);

        call.enqueue(new Callback<AddTourResponse>() {
            @Override
            public void onResponse(Call<AddTourResponse> call, Response<AddTourResponse> response) {
                if (response.isSuccessful()) {
                    AddTourResponse addTourResponse = response.body();
                    if (addTourResponse != null && addTourResponse.isSuccess()) {

                        Toast.makeText(DetailHotelActivity.this, addTourResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DetailHotelActivity.this, "Đặt khách sạn không thành công", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DetailHotelActivity.this, "Lỗi kết nối khi add Hotel", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddTourResponse> call, Throwable t) {
                Toast.makeText(DetailHotelActivity.this, "Gọi API thất bại", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
    private void anhxa()
    {
        btnBook = findViewById(R.id.btnBookHotel);
        txthotelId= findViewById(R.id.txthotelId);
        texthotelName= findViewById(R.id.texthotelName);
        texthotelAddress= findViewById(R.id.texthotelAddress);
        textViewprovince= findViewById(R.id.textViewprovince);
        textViewphoneNumber= findViewById(R.id.textViewVehicle);
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