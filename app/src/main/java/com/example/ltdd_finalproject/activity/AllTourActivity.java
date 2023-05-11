package com.example.ltdd_finalproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.adapters.TourAdapter;
import com.example.ltdd_finalproject.models.Tour;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AllTourActivity extends AppCompatActivity {
    Button allTourBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tour);

        RecyclerView recyclerView = findViewById(R.id.recycleViewTours);
        TourAdapter adapter = new TourAdapter();
        ///////////////

        adapter.setData(getListTour());
        recyclerView.setAdapter(adapter);
        //

    }
    private List<Tour> getListTour(){
        List<Tour> list = new ArrayList<>();
        list.add(new Tour("1", "1", "1", "Tour 1", "Place 1", LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 5), 2, BigDecimal.valueOf(200.0), "https://example.com/image1.jpg", true));
        list.add(new Tour("2", "2", "2", "Tour 2", "Place 2", LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 5), 3, BigDecimal.valueOf(300.0), "https://example.com/image2.jpg", false));
        return list;
    }
}