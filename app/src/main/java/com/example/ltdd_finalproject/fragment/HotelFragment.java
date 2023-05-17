package com.example.ltdd_finalproject.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.adapters.staffAdapter.sHotelAdapter;
import com.example.ltdd_finalproject.adapters.staffAdapter.sTourAdapter;
import com.example.ltdd_finalproject.models.Hotel;
import com.example.ltdd_finalproject.models.Tour;
import com.example.ltdd_finalproject.retro.API;
import com.example.ltdd_finalproject.retro.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelFragment extends Fragment {
    private RecyclerView recyclerView;
    private sHotelAdapter hotelAdapter;
    private List<Hotel> hotelList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doanh_thu, container, false);

        // Khởi tạo RecyclerView và TourAdapter
        recyclerView = view.findViewById(R.id.doanh_thu_list);
        hotelAdapter = new sHotelAdapter();

        // Đặt LayoutManager cho RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Thiết lập adapter cho RecyclerView
        recyclerView.setAdapter(hotelAdapter);

        // Gọi API để lấy danh sách các tour
        getTourList();

        return view;
    }

    private void getTourList() {
        // Gọi API để lấy danh sách các tour
        // Sau khi lấy được danh sách, cập nhật tourList và tourAdapter
        API apiService = RetrofitClient.getRetrofitLogin().create(API.class);
        Call<List<Hotel>> call = apiService.getHotels();
        call.enqueue(new Callback<List<Hotel>>() {
            @Override
            public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {

                hotelList = response.body();
                hotelAdapter.setTourList(hotelList);
                Log.d("retrofit_suc", response.body().toString());

            }

            @Override
            public void onFailure(Call<List<Hotel>> call, Throwable t) {
                // Xử lý lỗi
                Log.d("retrofit_error", t.toString());
            }
        });
    }

}
