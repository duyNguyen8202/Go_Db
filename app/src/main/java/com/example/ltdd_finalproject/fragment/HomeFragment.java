package com.example.ltdd_finalproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.activity.AllHotelActivity;
import com.example.ltdd_finalproject.activity.AllTourActivity;
import com.example.ltdd_finalproject.activity.AllVehicleActivity;
import com.example.ltdd_finalproject.adapters.CustomAdapter;
import com.example.ltdd_finalproject.models.Customer;
import com.example.ltdd_finalproject.models.Hotel;
import com.example.ltdd_finalproject.models.Tour;
import com.example.ltdd_finalproject.models.Vehicle;
import com.example.ltdd_finalproject.retro.API;
import com.example.ltdd_finalproject.retro.RetrofitClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    private List<Object> mdata;
    RecyclerView recyclerView;
    //////////
    private List<Hotel> hotelList = new ArrayList<>();
    private List<Tour> tourList = new ArrayList<>();
    private List<Vehicle> vehicleList = new ArrayList<>();
    Customer customer;
    Bundle bundle;
    String username;
    ///////////
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button allTourBtn, buttonHotel;
    Button allVehicleBtn;
    private String mParam1;
    private String mParam2;

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        allTourBtn = view.findViewById(R.id.buttonTous);
        allVehicleBtn = view.findViewById(R.id.buttonVehicle);
        buttonHotel = view.findViewById(R.id.buttonHotel);
        recyclerView = view.findViewById(R.id.rv_multipe_view_type);


//        apiTour();
//        apiVehicle();
        apiHotel();

        bundle = getArguments();
        if (bundle != null) {
            // retrieve the Customer object from the Bundle
            customer = (Customer) bundle.getSerializable("customer");
            username = (String) bundle.getSerializable("username");

        }
        return view;
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        allTourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AllTourActivity.class);
                intent.putExtra("customerid", customer.getCustomerId());
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
        allVehicleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AllVehicleActivity.class);
                intent.putExtra("customerid", customer.getCustomerId());
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
        buttonHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AllHotelActivity.class);
                intent.putExtra("customerid", customer.getCustomerId());
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

    }



//////////////////////////////////////
    protected void apiHotel() {
        API apiService = RetrofitClient.getRetrofit().create(API.class);
        Call<List<Hotel>> call = apiService.getHotels();
        call.enqueue(new Callback<List<Hotel>>() {
            @Override
            public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {
                hotelList = response.body();
                mdata = new ArrayList<>();
                mdata.addAll(hotelList);

                CustomAdapter customAdapter = new CustomAdapter(getContext(), mdata);
                recyclerView.setAdapter(customAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onFailure(Call<List<Hotel>> call, Throwable t) {
                // Xử lý lỗi
                Log.d("retrofit_error", t.toString());
            }
        });
    }

    protected void apiTour() {
        API apiService = RetrofitClient.getRetrofit().create(API.class);
        Call<List<Tour>> call = apiService.getTours();
        call.enqueue(new Callback<List<Tour>>() {
            @Override
            public void onResponse(Call<List<Tour>> call, Response<List<Tour>> response) {
                tourList = response.body();
                mdata = new ArrayList<>();
                mdata.addAll(tourList);

                CustomAdapter customAdapter = new CustomAdapter(getContext(), mdata);
                recyclerView.setAdapter(customAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onFailure(Call<List<Tour>> call, Throwable t) {
                // Xử lý lỗi
                Log.d("retrofit_error", t.toString());
            }
        });
    }

    protected void apiVehicle() {
        API apiService = RetrofitClient.getRetrofit().create(API.class);
        Call<List<Vehicle>> call = apiService.getVehicles();
        call.enqueue(new Callback<List<Vehicle>>() {
            @Override
            public void onResponse(Call<List<Vehicle>> call, Response<List<Vehicle>> response) {
                vehicleList = response.body();
                mdata = new ArrayList<>();
                mdata.addAll(vehicleList);

                CustomAdapter customAdapter = new CustomAdapter(getContext(), mdata);
                recyclerView.setAdapter(customAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onFailure(Call<List<Vehicle>> call, Throwable t) {
                // Xử lý lỗi
                Log.d("retrofit_error", t.toString());
            }
        });
    }


}
