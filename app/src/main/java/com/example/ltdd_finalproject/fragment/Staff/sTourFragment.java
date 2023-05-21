package com.example.ltdd_finalproject.fragment.Staff;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.adapters.staffAdapter.sBookingAdapter;
import com.example.ltdd_finalproject.adapters.staffAdapter.sTourAdapter;
import com.example.ltdd_finalproject.models.Booking;
import com.example.ltdd_finalproject.models.Tour;
import com.example.ltdd_finalproject.retro.API;
import com.example.ltdd_finalproject.retro.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class sTourFragment extends Fragment implements sTourAdapter.OnItemClickListener{

    private RecyclerView recyclerView;
    private sTourAdapter tourAdapter;
    private List<Tour> tourList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_staff_stask, container, false);

        // Khởi tạo RecyclerView và TourAdapter
        recyclerView = view.findViewById(R.id.doanh_thu_list);
        tourAdapter = new sTourAdapter(getActivity());

        // Đặt LayoutManager cho RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Thiết lập adapter cho RecyclerView
        recyclerView.setAdapter(tourAdapter);
        tourAdapter.setOnItemClickListener(this);

        // Gọi API để lấy danh sách các tour
        getTourList();
        tourAdapter.setOnItemClickListener(new sTourAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Tour tour) {
                // Handle the click event here
                Toast.makeText(getActivity(), "TourId " + tour.getTourId() + " is clicked", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onItemClick(Tour tour) {
        // Handle the click event here
        Toast.makeText(getActivity(), "TourId " + tour.getTourId() + " is clicked", Toast.LENGTH_SHORT).show();
    }

    private void getTourList() {
        // Gọi API để lấy danh sách các tour
        // Sau khi lấy được danh sách, cập nhật tourList và tourAdapter
        API apiService = RetrofitClient.getRetrofit().create(API.class);
        Call<List<Tour>> call = apiService.getTours();
        call.enqueue(new Callback<List<Tour>>() {
            @Override
            public void onResponse(Call<List<Tour>> call, Response<List<Tour>> response) {

                tourList = response.body();
                tourAdapter.setTourList(tourList);
                Log.d("retrofit_suc", response.body().toString());

            }

            @Override
            public void onFailure(Call<List<Tour>> call, Throwable t) {
                // Xử lý lỗi
                Log.d("retrofit_error", t.toString());
            }
        });
    }
}

