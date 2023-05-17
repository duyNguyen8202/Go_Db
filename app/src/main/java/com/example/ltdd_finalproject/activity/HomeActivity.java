package com.example.ltdd_finalproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.databinding.ActivityMain2Binding;
import com.example.ltdd_finalproject.fragment.BookingFragment;
import com.example.ltdd_finalproject.fragment.HomeFragment;
import com.example.ltdd_finalproject.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    ActivityMain2Binding binding;
    Button allTourBtn,buttonHotel;
    Button allVehicleBtn;
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        replaceFragment(new HomeFragment());
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomnavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.navigation_profile:
                    replaceFragment(new ProfileFragment());
                    break;
                case R.id.navigation_booking:
                    replaceFragment(new BookingFragment());
                    break;
            }
            return true;
        });

// Connect the NavController to the BottomNavigationView
        //anhXa();
        //setEvent();
    }
//    protected void anhXa(){
//
//        allTourBtn=(Button) findViewById(R.id.buttonTous);
//        allVehicleBtn=(Button) findViewById(R.id.buttonVehicle);
//        buttonHotel=(Button) findViewById(R.id.buttonHotel);
//    }
//    protected  void setEvent(){
//        //Event for Tours
//        allTourBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(HomeActivity.this, AllTourActivity.class);
//                startActivity(intent);
//            }
//        });
//        allVehicleBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(HomeActivity.this, AllVehicleActivity.class);
//                startActivity(intent);
//            }
//        });
//        buttonHotel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(HomeActivity.this, AllHotelActivity.class);
//                startActivity(intent);
//            }
//        });
//
//    }
}