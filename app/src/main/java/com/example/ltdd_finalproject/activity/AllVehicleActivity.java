package com.example.ltdd_finalproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.adapters.VehicleAdapter;
import com.example.ltdd_finalproject.models.Vehicle;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AllVehicleActivity extends AppCompatActivity {
    private GridView gridView;
    private VehicleAdapter vehicleAdapter;
    private List<Vehicle> vehicleArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_vehicle);
        SearchView searchView = findViewById(R.id.searchVehicle);
        gridView = (GridView) findViewById(R.id.gridview);
        themData();
        vehicleAdapter = new VehicleAdapter(AllVehicleActivity.this,vehicleArrayList, R.layout.vehicle_item );
        gridView.setAdapter(vehicleAdapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Do nothing on submit
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter the data set based on the search query
                vehicleAdapter.getFilter().filter(newText);
                return true;
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the selected vehicle item
                Vehicle vehicle = (Vehicle) adapterView.getItemAtPosition(i);
                // Pass the selected vehicle to a new activity to show its details
                Intent intent = new Intent(AllVehicleActivity.this, VehicleDetailActivity.class);
                intent.putExtra("vehicle", vehicle);
                startActivity(intent);
            }
        });
    }
    protected  void themData()
    {
        vehicleArrayList.add(new Vehicle("V01", "CTY01","HONDA-RS","RED-BLACK","89-B3-20113",new BigDecimal("250.00"),true));
        vehicleArrayList.add(new Vehicle("V02", "CTY01","HONDA-RS","WHITE-BLACK","89-B3-82613",new BigDecimal("150.00"),false));
        vehicleArrayList.add(new Vehicle("V03", "CTY02","YAMAHA-JANUS","RED-WHITE","22-B1-77113",new BigDecimal("3222.00"),true));
        vehicleArrayList.add(new Vehicle("V04", "CTY03","VINF","RED","23-H1-223312",new BigDecimal("9250.00"),true));

    }
}