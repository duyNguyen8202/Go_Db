package com.example.ltdd_finalproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ltdd_finalproject.R;

public class HomeActivity extends AppCompatActivity {
    Button allTourBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tour);
        anhXa();
        setEvent();
    }
    protected void anhXa(){
        allTourBtn=(Button) findViewById(R.id.buttonTous);
    }
    protected  void setEvent(){
        //Event for Tours
        allTourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AllTourActivity.class);
                startActivity(intent);
            }
        });
    }
}