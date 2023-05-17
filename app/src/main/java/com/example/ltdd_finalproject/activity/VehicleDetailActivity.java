package com.example.ltdd_finalproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.models.Vehicle;

public class VehicleDetailActivity extends AppCompatActivity {
    TextView txtvehicleId,textRentalCompany,textViewmodel,textViewColor,textViewLicensePlate,textViewrentalRate,textViewphoneavailable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_detail);

        Intent intent = getIntent();
        Vehicle vehicle = (Vehicle) intent.getSerializableExtra("vehicle");

        txtvehicleId= (TextView) findViewById(R.id.txtvehicleId);
        textRentalCompany= (TextView) findViewById(R.id.textRentalCompany);
        textViewmodel= (TextView) findViewById(R.id.textViewmodel);
        textViewColor= (TextView) findViewById(R.id.textViewColor);
        textViewLicensePlate= (TextView) findViewById(R.id.textViewLicensePlate);
        textViewrentalRate= (TextView) findViewById(R.id.textViewrentalRate);
        //textViewphoneavailable= (TextView) findViewById(R.id.textViewphoneavailable);


        txtvehicleId.setText(vehicle.getVehicleId());
        textRentalCompany.setText(vehicle.getRentalCompany());
        textViewmodel.setText(vehicle.getModel());
        textViewColor.setText(vehicle.getColor());
        textViewLicensePlate.setText(vehicle.getLicensePlate());
        textViewrentalRate.setText(String.valueOf(vehicle.getRentalRate()));
       // textViewphoneavailable.setText(a = (vehicle.get()) ? expressionTrue :  expressionFalse;);

    }
}