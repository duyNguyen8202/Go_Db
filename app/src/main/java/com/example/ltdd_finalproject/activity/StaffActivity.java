package com.example.ltdd_finalproject.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.adapters.MyPagerAdapter;
import com.example.ltdd_finalproject.fragment.DoanhThuFragment;
import com.example.ltdd_finalproject.fragment.HotelFragment;
import com.example.ltdd_finalproject.fragment.PlaceFragment;
import com.example.ltdd_finalproject.fragment.RoomFragment;
import com.example.ltdd_finalproject.fragment.TourFragment;
import com.example.ltdd_finalproject.fragment.VehicleFragment;
import com.example.ltdd_finalproject.models.Tour;
import com.example.ltdd_finalproject.retro.API;
import com.example.ltdd_finalproject.retro.RetrofitClient;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaffActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        // Khởi tạo ViewPager và PagerAdapter
        viewPager = findViewById(R.id.view_pager);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());

        pagerAdapter.addFragment(new TourFragment(), "Tour");
        pagerAdapter.addFragment(new HotelFragment(), "Khách sạn");
        pagerAdapter.addFragment(new RoomFragment(), "Phòng");
        pagerAdapter.addFragment(new VehicleFragment(), "Phương tiện");
        pagerAdapter.addFragment(new PlaceFragment(), "Địa điểm");
        pagerAdapter.addFragment(new DoanhThuFragment(), "Doanh thu");

        viewPager.setAdapter(pagerAdapter);

        // Khởi tạo TabLayout và kết nối với ViewPager
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }

}