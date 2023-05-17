package com.example.ltdd_finalproject.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.adapters.StaffPagerAdapter;
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


        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_pager);
        StaffPagerAdapter adapter = new StaffPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        GetAllTour();
    }

    private void GetAllTour() {
        API service = RetrofitClient.getRetrofitLogin().create(API.class);
        service.getTours().enqueue(new Callback<List<Tour>>() {
            @Override
            public void onResponse(Call<List<Tour>> call, Response<List<Tour>> response) {
                if (response.isSuccessful()) {
                    List<Tour> tours = response.body();
                    // Hiển thị danh sách tour lên ViewPager
                    StaffPagerAdapter adapter = new StaffPagerAdapter(getSupportFragmentManager(), tours);
                    ViewPager viewPager = findViewById(R.id.view_pager);
                    viewPager.setAdapter(adapter);
                    TabLayout tabLayout = findViewById(R.id.tab_layout);
                    tabLayout.setupWithViewPager(viewPager);
                } else {
                    // Xử lý lỗi khi gọi API không thành công
                    int statusCode = response.code();
                    if (statusCode == 404) {
                        Toast.makeText(getApplicationContext(), "Không tìm thấy dữ liệu", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Lỗi " + statusCode + " vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Tour>> call, Throwable t) {
                // Xử lý lỗi kết nối mạng hoặc lỗi khác
                Log.d("retrofit_error", t.toString());
            }
        });
    }
}