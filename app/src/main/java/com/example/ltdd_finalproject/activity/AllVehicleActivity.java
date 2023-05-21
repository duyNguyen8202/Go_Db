package com.example.ltdd_finalproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.adapters.VehicleAdapter;
import com.example.ltdd_finalproject.models.Vehicle;
import com.example.ltdd_finalproject.retro.API;
import com.example.ltdd_finalproject.retro.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllVehicleActivity extends AppCompatActivity {
    private GridView gridView;
    private VehicleAdapter vehicleAdapter;
    private List<Vehicle> vehicleList = new ArrayList<>();
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_vehicle);
        SearchView searchView = findViewById(R.id.searchVehicle);
        gridView = findViewById(R.id.gridview);
        username = getIntent().getStringExtra("username");
        themData();
        vehicleAdapter = new VehicleAdapter(AllVehicleActivity.this, vehicleList, R.layout.vehicle_item );
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
                Intent intent = new Intent(AllVehicleActivity.this, DetailVehicleActivity.class);
                intent.putExtra("vehicle", vehicle);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
    }
    protected  void themData()
    {
        API apiService = RetrofitClient.getRetrofit().create(API.class);
        Call<List<Vehicle>> call = apiService.getVehicles();
        call.enqueue(new Callback<List<Vehicle>>() {
            @Override
            public void onResponse(Call<List<Vehicle>> call, Response<List<Vehicle>> response) {
                vehicleList = response.body();
                vehicleAdapter.setVehicleList(vehicleList);

                for (int i = 0; i < vehicleList.size(); i++) {
                    Log.d("retrofit_suc", vehicleList.get(i).getModel());
                }
            }
            @Override
            public void onFailure(Call<List<Vehicle>> call, Throwable t) {
                // Xử lý lỗi
                Log.d("retrofit_error", t.toString());
            }
        });
    }
}