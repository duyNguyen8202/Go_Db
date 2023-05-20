package com.example.ltdd_finalproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.adapters.CustomAdapter;
import com.example.ltdd_finalproject.models.Hotel;
import com.example.ltdd_finalproject.models.Tour;
import com.example.ltdd_finalproject.models.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Object> mdata;
    Button allTourBtn;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        anhXa();
        setEvent();
        themData();
        CustomAdapter customAdapter=new CustomAdapter(this,mdata);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    protected void themData(){
        mdata=new ArrayList<>();
       // mdata.add(new Vehicle("V02", "CTY01","HONDA-RS","WHITE-BLACK","89-B3-82613",new BigDecimal("150.00"),false));
        mdata.add(new Hotel(
                "12345",
                "Grand Hotel",
                "123 Main Street",
                "California",
                "(123) 456-7890",
                "info@grandhotel.com",
                "www.grandhotel.com",
                "https://example.com/grandhotel.jpg"
        ));
//        mdata.add(new Vehicle("V03", "CTY02","YAMAHA-JANUS","RED-WHITE","22-B1-77113",new BigDecimal("3222.00"),true));
//
//        mdata.add(new Vehicle("V01", "CTY01","HONDA-RS","RED-BLACK","89-B3-20113",new BigDecimal("250.00"),true));
//
//       mdata.add(new Tour("1", "1", "1", "Tour 1", "Place 1", LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 5), 2, BigDecimal.valueOf(200.0), "https://example.com/image1.jpg", true));
//       mdata.add(new Vehicle("V04", "CTY03","VINF","RED","23-H1-223312",new BigDecimal("9250.00"),true));

        mdata.add(new Hotel(
                "12345",
                "Grand Hotel",
                "123 Main Street",
                "California",
                "(123) 456-7890",
                "info@grandhotel.com",
                "www.grandhotel.com",
                "https://example.com/grandhotel.jpg"
        ));
//       mdata.add(new Tour("2", "2", "2", "Tour 2", "Place 2", LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 5), 3, BigDecimal.valueOf(300.0), "https://example.com/image2.jpg", false));

    }
    protected void anhXa(){
        allTourBtn= findViewById(R.id.buttonTous);
        recyclerView = findViewById(R.id.rv_multipe_view_type);

    }
    protected  void setEvent(){
        //Event for Tours
        allTourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AllTourActivity.class);
                startActivity(intent);
            }
        });
    }
}