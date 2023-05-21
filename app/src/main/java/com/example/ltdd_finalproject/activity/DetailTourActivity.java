package com.example.ltdd_finalproject.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.models.AddTourResponse;
import com.example.ltdd_finalproject.models.Booking;
import com.example.ltdd_finalproject.models.RegisterResponse;
import com.example.ltdd_finalproject.models.SharedPreferences;
import com.example.ltdd_finalproject.models.Tour;
import com.example.ltdd_finalproject.retro.API;
import com.example.ltdd_finalproject.retro.RetrofitClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTourActivity extends AppCompatActivity {
    private API tourApi;
    TextView txttourId, texttourGuiderId, textplaceId, textViewtourName, textViewplaceGo, textViewdateGo, textViewdateBack, textViewnumPerson, textViewprice;
    Button btnBookNow;
    String username;
    private void themData() {

    }

    Tour tour;
    LocalDate currentDate = LocalDate.now();
    String date = currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    String customerID = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_detail);
        Intent intent = getIntent();
        tour = (Tour) intent.getSerializableExtra("tour");
        username= (String) intent.getStringExtra("username");
        // Khởi tạo đối tượng CustomerSharedPreferences
        customerID = SharedPreferences.getInstance(this).getCustomer().getCustomerId();

        anhxa();

        btnBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailTourActivity.this);
                builder.setTitle("Confirm Booking");
                builder.setMessage("Are you sure you want to book this tour?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        addTour();

                        Intent intent = new Intent(DetailTourActivity.this, HomeActivity.class);
                        intent.putExtra("tour", tour);
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

    public void addTour() {
        tourApi = RetrofitClient.getRetrofit().create(API.class);
        Call<AddTourResponse> call = tourApi.addTour(customerID, tour.getTourId(), date);

        call.enqueue(new Callback<AddTourResponse>() {
            @Override
            public void onResponse(Call<AddTourResponse> call, Response<AddTourResponse> response) {
                if (response.isSuccessful()) {
                    AddTourResponse addTourResponse = response.body();
                    if (addTourResponse != null && addTourResponse.isSuccess()) {

                        Toast.makeText(DetailTourActivity.this, addTourResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DetailTourActivity.this, "Thêm Tour không thành công", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DetailTourActivity.this, "Lỗi kết nối khi add Tour", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddTourResponse> call, Throwable t) {
                Toast.makeText(DetailTourActivity.this, "Gọi API thất bại", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }


    private void anhxa() {
        btnBookNow = findViewById(R.id.btnBookTour);
        txttourId = findViewById(R.id.txttourId);
        texttourGuiderId = findViewById(R.id.texttourGuiderId);
        textplaceId = findViewById(R.id.textplaceId);
        textViewtourName = findViewById(R.id.textViewtourName);
        textViewplaceGo = findViewById(R.id.textViewplaceGo);
        textViewdateGo = findViewById(R.id.textViewdateGo);
        textViewdateBack = findViewById(R.id.textViewdateBack);
        textViewnumPerson = findViewById(R.id.textViewnumPerson);
        textViewprice = findViewById(R.id.textViewprice);

        txttourId.setText(tour.getTourId());
        texttourGuiderId.setText(tour.getTourGuiderId());
        textplaceId.setText(tour.getPlaceId());
        textViewtourName.setText(tour.getTourName());
        textViewplaceGo.setText(tour.getPlaceGo());
        textViewdateGo.setText(String.valueOf(tour.getDateGo()));
        textViewdateBack.setText(String.valueOf(tour.getDateBack()));
        textViewnumPerson.setText(String.valueOf(tour.getNumPerson()));
        textViewprice.setText(String.valueOf(tour.getPrice()));
    }
}