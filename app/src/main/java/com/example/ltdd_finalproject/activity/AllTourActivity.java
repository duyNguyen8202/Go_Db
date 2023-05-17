package com.example.ltdd_finalproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.adapters.TourAdapter;
import com.example.ltdd_finalproject.models.Hotel;
import com.example.ltdd_finalproject.models.Tour;
import com.example.ltdd_finalproject.retro.API;
import com.example.ltdd_finalproject.retro.RetrofitClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllTourActivity extends AppCompatActivity {
    Button allTourBtn;
    private RecyclerView recyclerView;
    private TourAdapter mTourAdapter;
    private List<Tour> mTours = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tour);
        SearchView searchView = findViewById(R.id.searchTour);
        RecyclerView recyclerView = findViewById(R.id.recycleViewTours);

        mTourAdapter = new TourAdapter(this, mTours);
        recyclerView.setAdapter(mTourAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        themData ();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Do nothing on submit
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter the data set based on the search query
                mTourAdapter.filter(newText);
                return true;
            }
        });
        mTourAdapter.setOnItemClickListener(new TourAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Tour tour) {
                // Create an intent to start the detail activity
                Intent intent = new Intent(AllTourActivity.this, TourDetailActivity.class);
                // Pass the selected tour object to the detail activity
                intent.putExtra("tour", tour);

                // Start the detail activity
                startActivity(intent);
            }
        });

    }
    protected void themData () {

        API apiService = RetrofitClient.getRetrofitLogin().create(API.class);
        Call<List<Tour>> call = apiService.getTours();
        call.enqueue(new Callback<List<Tour>>() {
            @Override
            public void onResponse(Call<List<Tour>> call, Response<List<Tour>> response) {
                mTours = response.body();
                mTourAdapter.setTourList(mTours);

                for (int i = 0; i < mTours.size(); i++) {
                    Log.d("retrofit_suc", mTours.get(i).getTourName());
                }
            }

            @Override
            public void onFailure(Call<List<Tour>> call, Throwable t) {
                // Xử lý lỗi
                Log.d("retrofit_error", t.toString());
            }
        });
    }
}