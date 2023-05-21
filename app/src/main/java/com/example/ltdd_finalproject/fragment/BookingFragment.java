package com.example.ltdd_finalproject.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.adapters.BookingAdapter;
import com.example.ltdd_finalproject.models.Booking;
import com.example.ltdd_finalproject.retro.API;
import com.example.ltdd_finalproject.retro.RetrofitClient;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookingFragment extends Fragment {

    private ListView bookingListView;
    private BookingAdapter bookingAdapter;
    String customer_id;
    private Bundle bundle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking, container, false);
        if (bundle != null) {
            customer_id = (String) bundle.getSerializable("customer_id");
        }

        bookingListView = view.findViewById(R.id.listViewBooking);
        API api = RetrofitClient.getRetrofit().create(API.class);

        // Gọi phương thức getBooking() từ API
        Call<List<Booking>> call = api.getBooking(customer_id);
        call.enqueue(new Callback<List<Booking>>() {
            @Override
            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                if (getActivity() != null) {
                    // Add a null check for getActivity()
                    if (response.isSuccessful()) {
                        List<Booking> bookings = response.body();
                        if (bookings != null) {
                            // Cập nhật dữ liệu vào ListView
                            bookingAdapter = new BookingAdapter(getActivity(), bookings);
                            bookingListView.setAdapter(bookingAdapter);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Booking>> call, Throwable t) {
                if (getActivity() != null) {
                    // Add a null check for getActivity()
                    // Xử lý khi gọi API thất bại
                    if (t instanceof IOException) {
                        // Lỗi kết nối mạng
                        // Hiển thị thông báo lỗi cho người dùng
                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                    } else {
                        // Lỗi chung
                        // Hiển thị thông báo lỗi cho người dùng
                        Toast.makeText(getActivity(), "An error occurred. Please try again later.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }
}