package com.example.ltdd_finalproject.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.models.AddTourResponse;
import com.example.ltdd_finalproject.models.Booking;
import com.example.ltdd_finalproject.models.Staff;
import com.example.ltdd_finalproject.models.Tour;
import com.example.ltdd_finalproject.retro.API;
import com.example.ltdd_finalproject.retro.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailBookingActivity extends AppCompatActivity {

    TextView bookingId, customerId, staffId, bookingType, rentId, bookingDate;
    Button btnConfirm;
    Booking booking;
    String username;
    Staff staff;
    API api;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_booking);
        intent = getIntent();
        booking = (Booking) intent.getSerializableExtra("booking");
        username = intent.getStringExtra("username");


        staff = (Staff) intent.getSerializableExtra("staff");
        if (staff == null) {
            Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_SHORT).show();
        }
        anhxa();

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(booking.getStaffId() == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(DetailBookingActivity.this);
                    builder.setTitle("Confirm Booking");
                    builder.setMessage("Are you sure you want to confirm this booking?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            confirmBooking();
                            Intent intent = new Intent(DetailBookingActivity.this, StaffActivity.class);
                            intent.putExtra("username", username);
                            Toast.makeText(getApplicationContext(), "Booking confirmed", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("No", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Booking is confirmed!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void confirmBooking() {
        api = RetrofitClient.getRetrofit().create(API.class);
        Call<AddTourResponse> call = api.confirmBooking(booking.getBookingId(), staff.getStaffId());
        call.enqueue(new Callback<AddTourResponse>() {
            @Override
            public void onResponse(Call<AddTourResponse> call, Response<AddTourResponse> response) {
                if (response.isSuccessful()) {

                    AddTourResponse addTourResponse = response.body();
                    if (addTourResponse != null && addTourResponse.isSuccess()) {
                        Toast.makeText(DetailBookingActivity.this, "Xác nhận booking thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DetailBookingActivity.this, "Xác nhận booking thất bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DetailBookingActivity.this, "Lỗi kết nối khi xác nhận hoặc id sai", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddTourResponse> call, Throwable t) {
                Toast.makeText(DetailBookingActivity.this, "Gọi API thất bại", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }


    private void anhxa() {
        btnConfirm = findViewById(R.id.btnConfirm);

        bookingId = findViewById(R.id.bookingId);
        customerId = findViewById(R.id.customerId);
        staffId = findViewById(R.id.staffId);
        bookingType = findViewById(R.id.bookingType);
        rentId = findViewById(R.id.rentId);
        bookingDate = findViewById(R.id.bookingDate);

        bookingId.setText(booking.getBookingId());
        customerId.setText(booking.getCustomerId());
        staffId.setText(booking.getStaffId());
        bookingType.setText(booking.getBookingType());
        bookingDate.setText(booking.getBookingDate());

        if (booking.getBookingType().equals("Tour")) {
            rentId.setText(booking.getTourId());
        } else if (booking.getBookingType().equals("Vehicle")) {
            rentId.setText(booking.getVehicleId());
        } else {
            rentId.setText(booking.getHotelId());
        }

    }
}
