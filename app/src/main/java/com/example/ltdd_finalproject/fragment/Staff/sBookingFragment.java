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
import com.example.ltdd_finalproject.models.Booking;
import com.example.ltdd_finalproject.models.Hotel;
import com.example.ltdd_finalproject.retro.API;
import com.example.ltdd_finalproject.retro.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class sBookingFragment extends Fragment {

    private RecyclerView recyclerView;
    private sBookingAdapter bookingAdapter;
    private List<Booking> bookingList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doanh_thu, container, false);

        // Khởi tạo RecyclerView và TourAdapter
        recyclerView = view.findViewById(R.id.doanh_thu_list);
        bookingAdapter = new sBookingAdapter(getActivity());

        // Đặt LayoutManager cho RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Thiết lập adapter cho RecyclerView
        recyclerView.setAdapter(bookingAdapter);

        // Gọi API để lấy danh sách các tour
        getUnconfirmBooking();

        return view;
    }

    private void getUnconfirmBooking() {
        // Gọi API để lấy danh sách các tour
        // Sau khi lấy được danh sách, cập nhật tourList và tourAdapter
        API apiService = RetrofitClient.getRetrofit().create(API.class);
        Call<List<Booking>> call = apiService.getUnconfirmedBooking();
        call.enqueue(new Callback<List<Booking>>() {
            @Override
            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                bookingList = response.body();
                bookingAdapter.setTourList(bookingList);
            }

            @Override
            public void onFailure(Call<List<Booking>> call, Throwable t) {
                // Xử lý lỗi
                Log.d("retrofit_error", "lỗi lấy danh sách booking " + t.toString());
            }
        });
    }

//    private void getUnconfirmBooking() {
//        // Gọi API để lấy danh sách các tour
//        // Sau khi lấy được danh sách, cập nhật tourList và tourAdapter
//        API apiService = RetrofitClient.getRetrofit().create(API.class);
//        Call<List<Booking>> call = apiService.getUnconfirmedBooking();
//        call.enqueue(new Callback<List<Booking>>() {
//            @Override
//            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
//                bookingList = response.body();
//                bookingAdapter.setTourList(bookingList);
//            }
//
//            @Override
//            public void onFailure(Call<List<Booking>> call, Throwable t) {
//                // Xử lý lỗi
//                Log.d("retrofit_error", "lỗi lấy danh sách booking " + t.toString());
//            }
//        });
//    }

}
