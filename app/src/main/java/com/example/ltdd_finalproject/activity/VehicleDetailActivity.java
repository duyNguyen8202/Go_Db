package com.example.ltdd_finalproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.models.Vehicle;

public class VehicleDetailActivity extends AppCompatActivity {
    TextView txtvehicleId,textRentalCompany,textViewmodel,textViewColor,textViewLicensePlate,textViewrentalRate,textViewphoneavailable;
    String customerid = new String();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_detail);

        Intent intent = getIntent();
        Vehicle vehicle = (Vehicle) intent.getSerializableExtra("vehicle");
        customerid = getIntent().getStringExtra("customerid");
        if(customerid !=null)
        {
            Toast.makeText(this, "ID:"+customerid, Toast.LENGTH_SHORT).show();
        }
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

    }
}