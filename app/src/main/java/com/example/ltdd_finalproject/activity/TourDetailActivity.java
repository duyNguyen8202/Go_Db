package com.example.ltdd_finalproject.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.models.Hotel;
import com.example.ltdd_finalproject.models.Tour;

public class TourDetailActivity extends AppCompatActivity {
    TextView txttourId,texttourGuiderId,textplaceId,textViewtourName,textViewplaceGo,textViewdateGo,textViewdateBack,textViewnumPerson,textViewprice;
    Button btnBookNow;
    private void themData(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_detail);
        Intent intent = getIntent();
        Tour tour = (Tour) intent.getSerializableExtra("tour");
        btnBookNow = findViewById(R.id.btnBookTour);
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

        btnBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TourDetailActivity.this);
                builder.setTitle("Confirm Booking");
                builder.setMessage("Are you sure you want to book this tour?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(TourDetailActivity.this, HomeActivity.class);
                        intent.putExtra("tour", tour);
                        intent.putExtra("fragment", "booking_fragment");
                        Toast.makeText(getApplicationContext(), "Booking confirmed", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}