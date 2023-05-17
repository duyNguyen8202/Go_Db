package com.example.ltdd_finalproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.adapters.TourAdapter;
import com.example.ltdd_finalproject.models.Hotel;
import com.example.ltdd_finalproject.models.Tour;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AllTourActivity extends AppCompatActivity {
    Button allTourBtn;
    private RecyclerView recyclerView;
    private TourAdapter mTourAdapter;
    private final List<Tour> mTours = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tour);
        SearchView searchView = findViewById(R.id.searchTour);
        RecyclerView recyclerView = findViewById(R.id.recycleViewTours);

//        mTours.add(new Tour("1", "1", "1", "Tour 1", "Place 1", LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 5), 2, BigDecimal.valueOf(200.0), "https://example.com/image1.jpg", true));
//        mTours.add(new Tour("2", "2", "2", "Tour 2", "Place 2", LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 5), 3, BigDecimal.valueOf(300.0), "https://example.com/image2.jpg", false));

        mTourAdapter = new TourAdapter(this,mTours);
        recyclerView.setAdapter(mTourAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        ///////////////
        recyclerView.setLayoutManager(linearLayoutManager);
        //
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
}