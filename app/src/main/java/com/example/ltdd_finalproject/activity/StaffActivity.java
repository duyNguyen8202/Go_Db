package com.example.ltdd_finalproject.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.fragment.Staff.DoanhThuFragment;
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
    TextView txt_username;
    String usernameStaff;
    Staff staff;
    TextView username;
    ImageView image;
    Button btnLogout;
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        usernameStaff = getIntent().getStringExtra("username");
        getStaff();

        khoiTaoToolBar();

        btnLogout = findViewById(R.id.btn_logout_main);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(StaffActivity.this);
                builder.setTitle("Logout");
                builder.setMessage("Do you want to exit?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(StaffActivity.this, IntroActivity.class);

                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    public void getStaff() {
        API apiService = RetrofitClient.getRetrofit().create(API.class);
        Call<Staff> call = apiService.getStaff(usernameStaff);
        call.enqueue(new Callback<Staff>() {
            @Override
            public void onResponse(Call<Staff> call, Response<Staff> response) {
                staff = response.body();
                anhxa();

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
        txt_username = findViewById(R.id.txt_username);
        txt_username.setText(usernameStaff);
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
        DoanhThuFragment doanhThuFragment = new DoanhThuFragment();

        doanhThuFragment.setArguments(bundle);
        pagerAdapter.addFragment(new DoanhThuFragment(), "Doanh thu");

        viewPager.setAdapter(pagerAdapter);

        // Khởi tạo TabLayout và kết nối với ViewPager
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void anhxa() {
        username = findViewById(R.id.txt_username);
        image = findViewById(R.id.img_profile_main);

        username.setText(String.valueOf(staff.getFullName()));
        Glide.with(getApplication())
                .load(staff.getImageLink())
                .apply(new RequestOptions().override(250, 250))
                .into(image);

    }

}