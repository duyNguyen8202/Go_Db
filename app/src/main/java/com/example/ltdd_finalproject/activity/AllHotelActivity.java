package com.example.ltdd_finalproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.adapters.HotelAdapter;
import com.example.ltdd_finalproject.models.Hotel;
import com.example.ltdd_finalproject.retro.API;
import com.example.ltdd_finalproject.retro.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AllHotelActivity extends AppCompatActivity {
    private ListView listView;
    private HotelAdapter hotelAdapter;
    private List<Hotel> hotelList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_hotel);
        SearchView searchView = findViewById(R.id.searchHotel);
        listView = findViewById(R.id.listviewHotel);
        hotelAdapter = new HotelAdapter(AllHotelActivity.this, hotelList, R.layout.hotel_item1);
        listView.setAdapter(hotelAdapter);
        themData();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Do nothing on submit
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter the data set based on the search query
                hotelAdapter.filter(newText);
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the selected vehicle item
                Hotel hotel = (Hotel) adapterView.getItemAtPosition(i);
                // Pass the selected vehicle to a new activity to show its details
                Intent intent = new Intent(AllHotelActivity.this, HotelDetailActivity.class);
                intent.putExtra("hotel", hotel);
                startActivity(intent);
            }
        });
    }

    protected void themData() {

        API apiService = RetrofitClient.getRetrofitLogin().create(API.class);
        Call<List<Hotel>> call = apiService.getHotels();
        call.enqueue(new Callback<List<Hotel>>() {
            @Override
            public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {
                hotelList = response.body();
                hotelAdapter.setHotelList(hotelList);

                for(int i=0; i<hotelList.size(); i++)
                {
                    Log.d("retrofit_suc", hotelList.get(i).getHotelName());
                }
            }
            @Override
            public void onFailure(Call<List<Hotel>> call, Throwable t) {
                // Xử lý lỗi
                Log.d("retrofit_error", t.toString());
            }
        });

    }
}
