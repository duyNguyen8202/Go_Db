package com.example.ltdd_finalproject.fragment.Staff;

import android.content.Intent;
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
import com.example.ltdd_finalproject.activity.DetailBookingActivity;
import com.example.ltdd_finalproject.adapters.staffAdapter.sBookingAdapter;
import com.example.ltdd_finalproject.models.Booking;
import com.example.ltdd_finalproject.models.Staff;
import com.example.ltdd_finalproject.retro.API;
import com.example.ltdd_finalproject.retro.RetrofitClient;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class sBookingFragment extends Fragment implements sBookingAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private sBookingAdapter bookingAdapter;
    private List<Booking> bookingList;
//    private final String choice;
    private String username;
    private Staff staff;

    Bundle bundle = new Bundle();

//    public sBookingFragment(final String choice) {
//        this.choice = choice;
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_staff_stask, container, false);

        bundle = getArguments();
        if (bundle != null) {
            username = (String) bundle.getSerializable("username");
            Log.d("Username", "staff.getStaffId()");
            staff = (Staff) bundle.getSerializable("staff");
        }
        if (staff != null) {
            Log.d("staffBooking", staff.getStaffId());
        }
        //

        recyclerView = view.findViewById(R.id.doanh_thu_list);
        bookingAdapter = new sBookingAdapter(getActivity());
        // Đặt LayoutManager cho RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Thiết lập adapter và item click listener cho RecyclerView
        recyclerView.setAdapter(bookingAdapter);
        bookingAdapter.setOnItemClickListener(this);

//        if (choice.equals("no")) {
//            getUnconfirmBooking();
//        } else {
//            getConfirmBooking();
//        }
        getUnconfirmBooking();
        bookingAdapter.setOnItemClickListener(new sBookingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Booking booking) {
                Intent intent = new Intent(getActivity(), DetailBookingActivity.class);
                intent.putExtra("booking", booking);
                intent.putExtra("username", username);
                intent.putExtra("staff", staff);

                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onItemClick(Booking booking) {
        // Handle the click event here
    }


    private void getUnconfirmBooking() {
        // Gọi API để lấy danh sách các booking
        API apiService = RetrofitClient.getRetrofit().create(API.class);
        Call<List<Booking>> call = apiService.getUnconfirmedBooking();
        call.enqueue(new Callback<List<Booking>>() {
            @Override
            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                bookingList = response.body();
                bookingAdapter.setBookingList(bookingList);

                bookingAdapter.setOnItemClickListener(new sBookingAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Booking booking) {
                        Intent intent = new Intent(getActivity(), DetailBookingActivity.class);
                        intent.putExtra("booking", booking);
                        intent.putExtra("username", username);
                        if (staff != null) {
                            Log.d("sbooking", staff.getStaffId());
                            intent.putExtra("staff", staff);
                        }
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Booking>> call, Throwable t) {
                // Xử lý lỗi
                Log.d("retrofit_error", "lỗi lấy danh sách booking " + t);
            }
        });
    }

//    private void getConfirmBooking() {
//        // Gọi API để lấy danh sách các booking
//        API apiService = RetrofitClient.getRetrofit().create(API.class);
//        Call<List<Booking>> call = apiService.getConfirmedBooking();
//        call.enqueue(new Callback<List<Booking>>() {
//            @Override
//            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
//                bookingList = response.body();
//                bookingAdapter.setBookingList(bookingList);
//                if (bundle != null) {
//                    username = (String) bundle.getSerializable("username");
//                    staff = (Staff) bundle.getSerializable("staff");
//                }
//                if (staff != null) {
//                    Log.d("staffBooking", staff.getStaffId());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Booking>> call, Throwable t) {
//                // Xử lý lỗi
//                Log.d("retrofit_error", "lỗi lấy danh sách booking " + t);
//            }
//        });
//    }


}
