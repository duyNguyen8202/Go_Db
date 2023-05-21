package com.example.ltdd_finalproject.fragment.Staff;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.adapters.staffAdapter.sVehicleAdapter;
import com.example.ltdd_finalproject.models.Vehicle;
import com.example.ltdd_finalproject.retro.API;
import com.example.ltdd_finalproject.retro.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleFragment extends Fragment {
    private RecyclerView recyclerView;
    private sVehicleAdapter vehicleAdapter;
    private List<Vehicle> vehicleList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doanh_thu, container, false);

        // Khởi tạo RecyclerView và TourAdapter
        recyclerView = view.findViewById(R.id.doanh_thu_list);
        vehicleAdapter = new sVehicleAdapter();

        // Đặt LayoutManager cho RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Thiết lập adapter cho RecyclerView
        recyclerView.setAdapter(vehicleAdapter);

        // Gọi API để lấy danh sách các tour
        getTourList();

        return view;
    }

    private void getTourList() {
        // Gọi API để lấy danh sách các tour
        // Sau khi lấy được danh sách, cập nhật tourList và tourAdapter
        API apiService = RetrofitClient.getRetrofit().create(API.class);
        Call<List<Vehicle>> call = apiService.getVehicles();
        call.enqueue(new Callback<List<Vehicle>>() {
            @Override
            public void onResponse(Call<List<Vehicle>> call, Response<List<Vehicle>> response) {

                vehicleList = response.body();
                vehicleAdapter.setTourList(vehicleList);
                Log.d("retrofit_suc", response.body().toString());

            }

            @Override
            public void onFailure(Call<List<Vehicle>> call, Throwable t) {
                // Xử lý lỗi
                Log.d("retrofit_error", t.toString());
            }
        });
    }

}
