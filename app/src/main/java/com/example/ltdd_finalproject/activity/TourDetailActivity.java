package com.example.ltdd_finalproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.models.Hotel;
import com.example.ltdd_finalproject.models.Tour;

public class TourDetailActivity extends AppCompatActivity {
    TextView txttourId,texttourGuiderId,textplaceId,textViewtourName,textViewplaceGo,textViewdateGo,textViewdateBack,textViewnumPerson,textViewprice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_detail);
        Intent intent = getIntent();
        Tour tour = (Tour) intent.getSerializableExtra("tour");

        txttourId= (TextView) findViewById(R.id.txttourId);
        texttourGuiderId= (TextView) findViewById(R.id.texttourGuiderId);
        textplaceId= (TextView) findViewById(R.id.textplaceId);
        textViewtourName= (TextView) findViewById(R.id.textViewtourName);
        textViewplaceGo= (TextView) findViewById(R.id.textViewplaceGo);
        textViewdateGo= (TextView) findViewById(R.id.textViewdateGo);
        textViewdateBack= (TextView) findViewById(R.id.textViewdateBack);
        textViewnumPerson= (TextView) findViewById(R.id.textViewnumPerson);
        textViewprice= (TextView) findViewById(R.id.textViewprice);

        txttourId.setText(tour.getTourId());
        texttourGuiderId.setText(tour.getTourGuiderId());
        textplaceId.setText(tour.getPlaceId());
        textViewtourName.setText(tour.getTourName());
        textViewplaceGo.setText(tour.getPlaceGo());
        textViewdateGo.setText(String.valueOf(tour.getDateGo()));
        textViewdateBack.setText(String.valueOf(tour.getDateBack()));
        textViewnumPerson.setText(String.valueOf(tour.getNumPerson()));
        textViewprice.setText(String.valueOf(tour.getPrice()));
    }
}