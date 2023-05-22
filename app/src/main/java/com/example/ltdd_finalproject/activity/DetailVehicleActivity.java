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
import com.example.ltdd_finalproject.models.SharedPreferences;
import com.example.ltdd_finalproject.models.Vehicle;
import com.example.ltdd_finalproject.retro.API;
import com.example.ltdd_finalproject.retro.RetrofitClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailVehicleActivity extends AppCompatActivity {
    TextView txtvehicleId,textRentalCompany,textViewmodel,textViewColor,textViewLicensePlate,textViewrentalRate;
    private API  vehicleApi;
    String customerID = null;
    Vehicle vehicle;
    Intent intent;
    Button btnBookV;
    String username;
    LocalDate currentDate = LocalDate.now();
    String date = currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_vehicle);
        intent = getIntent();
        if (intent != null) {
            intent = getIntent();
            vehicle = (Vehicle) intent.getSerializableExtra("vehicle");
            username= intent.getStringExtra("username");
            Log.d("AllVehicle","không xuất hiện");
        }
        else {
            finish();
        }
        // Khởi tạo đối tượng CustomerSharedPreferences
        customerID = intent.getStringExtra("customerid");
        btnBookV = findViewById(R.id.btnBookVehicle);

        txtvehicleId= findViewById(R.id.txtvehicleId);
        textRentalCompany= findViewById(R.id.textRentalCompany);
        textViewmodel= findViewById(R.id.textViewmodel);
        textViewColor= findViewById(R.id.textViewColor);
        textViewLicensePlate= findViewById(R.id.textViewLicensePlate);
        textViewrentalRate= findViewById(R.id.textViewrentalRate);
        //textViewphoneavailable= (TextView) findViewById(R.id.textViewphoneavailable);


        txtvehicleId.setText(vehicle.getVehicleId());
        textRentalCompany.setText(vehicle.getRentalCompany());
        textViewmodel.setText(vehicle.getModel());
        textViewColor.setText(vehicle.getColor());
        textViewLicensePlate.setText(vehicle.getLicensePlate());
        textViewrentalRate.setText(String.valueOf(vehicle.getRentalRate()));
       // textViewphoneavailable.setText(a = (vehicle.get()) ? expressionTrue :  expressionFalse;);
        btnBookV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailVehicleActivity.this);
                builder.setTitle("Confirm Booking");
                builder.setMessage("Are you sure you want to book this tour?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        addVehicle();

                        Intent intent = new Intent(DetailVehicleActivity.this, HomeActivity.class);
                        intent.putExtra("vehicle", vehicle);
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
    public void addVehicle() {
        vehicleApi = RetrofitClient.getRetrofit().create(API.class);
        Call<AddTourResponse> call = vehicleApi.addVehicle(customerID, vehicle.getVehicleId(), date);

        call.enqueue(new Callback<AddTourResponse>() {
            @Override
            public void onResponse(Call<AddTourResponse> call, Response<AddTourResponse> response) {
                if (response.isSuccessful()) {
                    AddTourResponse addTourResponse = response.body();
                    if (addTourResponse != null && addTourResponse.isSuccess()) {

                        Toast.makeText(DetailVehicleActivity.this, addTourResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DetailVehicleActivity.this, "Thêm Vehicle không thành công", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DetailVehicleActivity.this, "Lỗi kết nối khi add Vehicle", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddTourResponse> call, Throwable t) {
                Toast.makeText(DetailVehicleActivity.this, "Gọi API thất bại", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}