package com.example.ltdd_finalproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ltdd_finalproject.R;

public class RegisActivity extends AppCompatActivity {
    Button buttonNext;
    EditText editTextUsername,editTextPassword,editTextRePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);
        anhXa();
        setEvent();
    }
    protected void setEvent(){
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisActivity.this, RegistInfoActivity.class);
                startActivity(intent);
            }
        });
    }
    protected void anhXa(){

        buttonNext = findViewById(R.id.buttonNext);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextRePassword = findViewById(R.id.editTextRePassword);
    }
}