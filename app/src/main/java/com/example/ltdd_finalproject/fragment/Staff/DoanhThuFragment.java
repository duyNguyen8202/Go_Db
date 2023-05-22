package com.example.ltdd_finalproject.fragment.Staff;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.adapters.staffAdapter.sBookingAdapter;
import com.example.ltdd_finalproject.models.Booking;
import com.example.ltdd_finalproject.models.Staff;
import com.example.ltdd_finalproject.retro.API;
import com.example.ltdd_finalproject.retro.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoanhThuFragment extends Fragment {
    private sBookingAdapter bookingAdapter;
    private TextView textViewTongBooking, textViewTour, textViewVehicle, textViewHotel;
    private List<Booking> bookingList;
    //    private final String choice;
    private String username;
    private Staff staff;
    Bundle bundle = new Bundle();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.doanhthu_fragment, container, false);
        // Khởi tạo các thành phần giao diện cho fragment này
        bundle = getArguments();
        if (bundle != null) {
            username = (String) bundle.getSerializable("username");
            Log.d("Username", "staff.getStaffId()");
            staff = (Staff) bundle.getSerializable("staff");
        }
        if (staff != null) {
            Log.d("staffBooking", staff.getStaffId());
        }
//        bookingAdapter = new sBookingAdapter(getActivity());
        getConfirmBooking();
        if (bookingList != null) {
            Log.d("Booking In Doanhthu", "staff.getStaffId()");
        }
        textViewTongBooking = view.findViewById(R.id.textViewTongBooking);
        textViewTour = view.findViewById(R.id.textViewTour);
        textViewVehicle = view.findViewById(R.id.textViewVehicle);
        textViewHotel = view.findViewById(R.id.textViewHotel);


        return view;
    }

    private void setUI() {
        int demH = 0, demV = 0, demT = 0;
        if (bookingList != null) {
            textViewTongBooking.setText(String.valueOf(bookingList.size()));

            for (Booking booking : bookingList) {
                Log.d("Type", booking.getBookingType());
                switch (booking.getBookingType()) {

                    case "Hotel":
                        demH++;
                        break;
                    case "Vehicle":
                        demV++;
                        break;
                    case "Tour":
                        demT++;
                        break;
                }
            }
        } else {
            Log.d("setUI", "bookingList is null");
        }
        textViewHotel.setText(String.valueOf(demH));
        textViewVehicle.setText(String.valueOf(demV));
        textViewTour.setText(String.valueOf(demT));
    }

    private void getConfirmBooking() {
        // Gọi API để lấy danh sách các booking
        API apiService = RetrofitClient.getRetrofit().create(API.class);
        Call<List<Booking>> call = apiService.getConfirmedBooking();
        call.enqueue(new Callback<List<Booking>>() {
            @Override
            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                bookingList = response.body();
                if(bookingList != null)
                {
                    Log.d("BookingList api", "taffId()");
                    setUI();
                }
                //bookingAdapter.setBookingList(bookingList);
                if (bundle != null) {
                    username = (String) bundle.getSerializable("username");
                    staff = (Staff) bundle.getSerializable("staff");
                }
                if (staff != null) {
                    Log.d("staffBooking", staff.getStaffId());
                }
            }

            @Override
            public void onFailure(Call<List<Booking>> call, Throwable t) {
                // Xử lý lỗi
                Log.d("retrofit_error", "lỗi lấy danh sách booking " + t);
            }
        });
    }
}

