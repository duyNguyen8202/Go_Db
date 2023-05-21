package com.example.ltdd_finalproject.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.adapters.staffAdapter.MyPagerAdapter;
import com.example.ltdd_finalproject.fragment.Staff.sBookingConfirmedFragment;
import com.example.ltdd_finalproject.fragment.Staff.sHotelFragment;
import com.example.ltdd_finalproject.fragment.Staff.sPlaceFragment;
import com.example.ltdd_finalproject.fragment.Staff.sTourFragment;
import com.example.ltdd_finalproject.fragment.Staff.sVehicleFragment;
import com.example.ltdd_finalproject.fragment.Staff.sBookingFragment;
import com.example.ltdd_finalproject.models.Staff;
import com.example.ltdd_finalproject.retro.API;
import com.example.ltdd_finalproject.retro.RetrofitClient;
import com.google.android.material.tabs.TabLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaffActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    String usernameStaff;
    Staff staff;
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        usernameStaff = getIntent().getStringExtra("username");
        getStaff();
        khoiTaoToolBar();
    }

    public void getStaff() {
        API apiService = RetrofitClient.getRetrofit().create(API.class);
        Call<Staff> call = apiService.getStaff(usernameStaff);
        call.enqueue(new Callback<Staff>() {
            @Override
            public void onResponse(Call<Staff> call, Response<Staff> response) {
                staff = response.body();
                bundle.putSerializable("username", usernameStaff);
                bundle.putSerializable("staff", staff);
            }

            @Override
            public void onFailure(Call<Staff> call, Throwable t) {
                // Xử lý lỗi
                Toast.makeText(StaffActivity.this, "Lỗi gọi api staff trong staff activity", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void khoiTaoToolBar() {

        // Khởi tạo ViewPager và PagerAdapter
        viewPager = findViewById(R.id.view_pager);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());

        // booking
        sBookingFragment sBookingFragmentNo = new sBookingFragment();
        sBookingFragmentNo.setArguments(bundle);
        pagerAdapter.addFragment(sBookingFragmentNo, "Chưa xác nhận");

        sBookingConfirmedFragment sBookingFragmentYes = new sBookingConfirmedFragment();
        sBookingFragmentYes.setArguments(bundle);
        pagerAdapter.addFragment(sBookingFragmentYes, "Đã xác nhận");

        // tour
        sTourFragment sTourFragment = new sTourFragment();
        sTourFragment.setArguments(bundle);
        pagerAdapter.addFragment(sTourFragment, "Tour");

        // hotel
        sHotelFragment sHotelFragment = new sHotelFragment();
        sHotelFragment.setArguments(bundle);
        pagerAdapter.addFragment(sHotelFragment, "Khách sạn");

//        pagerAdapter.addFragment(new RoomFragment(), "Phòng");

        // Vehicle
        sVehicleFragment sVehicleFragment = new sVehicleFragment();
        sVehicleFragment.setArguments(bundle);
        pagerAdapter.addFragment(sVehicleFragment, "Phương tiện");

        // place
        sPlaceFragment sPlaceFragment = new sPlaceFragment();
        sPlaceFragment.setArguments(bundle);
        pagerAdapter.addFragment(new sPlaceFragment(), "Địa điểm");

        // doanh thu
//        pagerAdapter.addFragment(new DoanhThuFragment(), "Doanh thu");

        viewPager.setAdapter(pagerAdapter);

        // Khởi tạo TabLayout và kết nối với ViewPager
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

}