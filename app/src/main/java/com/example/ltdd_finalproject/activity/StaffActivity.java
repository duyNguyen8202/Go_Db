package com.example.ltdd_finalproject.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.adapters.MyPagerAdapter;
import com.example.ltdd_finalproject.fragment.Staff.DoanhThuFragment;
import com.example.ltdd_finalproject.fragment.Staff.HotelFragment;
import com.example.ltdd_finalproject.fragment.Staff.PlaceFragment;
import com.example.ltdd_finalproject.fragment.Staff.TourFragment;
import com.example.ltdd_finalproject.fragment.Staff.VehicleFragment;
import com.example.ltdd_finalproject.fragment.Staff.sBookingFragment;
import com.google.android.material.tabs.TabLayout;

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

        pagerAdapter.addFragment(new sBookingFragment(), "Chưa xác nhận");
        pagerAdapter.addFragment(new sBookingFragment(), "Đã xác nhận");
        pagerAdapter.addFragment(new TourFragment(), "Tour");
        pagerAdapter.addFragment(new HotelFragment(), "Khách sạn");
//        pagerAdapter.addFragment(new RoomFragment(), "Phòng");
        pagerAdapter.addFragment(new VehicleFragment(), "Phương tiện");
        pagerAdapter.addFragment(new PlaceFragment(), "Địa điểm");
        pagerAdapter.addFragment(new DoanhThuFragment(), "Doanh thu");

        viewPager.setAdapter(pagerAdapter);

        // Khởi tạo TabLayout và kết nối với ViewPager
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }

}