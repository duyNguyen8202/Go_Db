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
import com.example.ltdd_finalproject.adapters.HotelAdapter;
import com.example.ltdd_finalproject.adapters.TourAdapter;
import com.example.ltdd_finalproject.adapters.VehicleAdapter;
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
    Customer customer;
    //////////
    private List<Hotel> hotelList = new ArrayList<>();
    private List<Tour> tourList = new ArrayList<>();
    private List<Vehicle> vehicleList = new ArrayList<>();
    Bundle bundle= new Bundle();

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

        themData();

        recyclerView = view.findViewById(R.id.rv_multipe_view_type);
        CustomAdapter customAdapter = new CustomAdapter(getContext(), mdata);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        bundle = getArguments();
        if (bundle != null) {
            // retrieve the Customer object from the Bundle
            customer = (Customer) bundle.getSerializable("customer");

            if (customer != null) {
                // do something with the Customer object
                Toast.makeText(getContext(), "Customer object is exist", Toast.LENGTH_SHORT).show();
            } else {
                // handle the case where the customer object is null
                Toast.makeText(getContext(), "Customer object is null", Toast.LENGTH_SHORT).show();
            }
        }
        return view;
    }

    protected void themData() {

        apiHotel();
        apiTour();
        apiVehicle();

        mdata=new ArrayList<>();
        // mdata.add(new Vehicle("V02", "CTY01","HONDA-RS","WHITE-BLACK","89-B3-82613",new BigDecimal("150.00"),false));
        mdata.add(new Hotel(
                "12345",
                "Grand Hotel",
                "123 Main Street",
                "California",
                "(123) 456-7890",
                "info@grandhotel.com",
                "www.grandhotel.com",
                "https://example.com/grandhotel.jpg"
        ));
        mdata.add(new Vehicle("V03", "CTY02","YAMAHA-JANUS","RED-WHITE","22-B1-77113",new BigDecimal("3222.00"),true));

        mdata.add(new Vehicle("V01", "CTY01","HONDA-RS","RED-BLACK","89-B3-20113",new BigDecimal("250.00"),true));

//    mdata.add(new Tour("1", "1", "1", "Tour 1", "Place 1", LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 5), 2, BigDecimal.valueOf(200.0), "https://example.com/image1.jpg", true));
        mdata.add(new Vehicle("V04", "CTY03","VINF","RED","23-H1-223312",new BigDecimal("9250.00"),true));

        mdata.add(new Hotel(
                "12345",
                "Grand Hotel",
                "123 Main Street",
                "California",
                "(123) 456-7890",
                "info@grandhotel.com",
                "www.grandhotel.com",
                "https://example.com/grandhotel.jpg"
        ));
//    mdata.add(new Tour("2", "2", "2", "Tour 2", "Place 2", LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 5), 3, BigDecimal.valueOf(300.0), "https://example.com/image2.jpg", false));

//        int sizeList = checkSizeList();
//
//        int j = 0;
//        for (int i = 0; i < sizeList; i++) {
//            if (i < hotelList.size()) {
//                mdata.add(hotelList.get(j));
//                j++;
//            }
//            if (i < tourList.size()) {
//                mdata.add(tourList.get(j));
//                j++;
//            }
//            if (j < vehicleList.size()) {
//                mdata.add(vehicleList.get(j));
//                j++;
//            }
//        }

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        allTourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AllTourActivity.class);
                intent.putExtra("customerid", customer.getCustomerId());
                startActivity(intent);
            }
        });
        allVehicleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AllVehicleActivity.class);
                intent.putExtra("customerid", customer.getCustomerId());
                startActivity(intent);
            }
        });
        buttonHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AllHotelActivity.class);
                intent.putExtra("customerid", customer.getCustomerId());
                startActivity(intent);
            }
        });

    }

//////////////////////////////////////
    protected void apiHotel() {
        API apiService = RetrofitClient.getRetrofitLogin().create(API.class);
        Call<List<Hotel>> call = apiService.getHotels();
        call.enqueue(new Callback<List<Hotel>>() {
            @Override
            public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {
                hotelList = response.body();
//                hotelAdapter.setHotelList(hotelList);

                for (int i = 0; i < hotelList.size(); i++) {
                    Log.d("retrofit_suc", hotelList.get(i).getHotelName());
                }
            }

            @Override
            public void onFailure(Call<List<Hotel>> call, Throwable t) {
                // Xử lý lỗi
                Log.d("retrofit_error", t.toString());
            }
        });
    }

    protected void apiTour() {
        API apiService = RetrofitClient.getRetrofitLogin().create(API.class);
        Call<List<Tour>> call = apiService.getTours();
        call.enqueue(new Callback<List<Tour>>() {
            @Override
            public void onResponse(Call<List<Tour>> call, Response<List<Tour>> response) {
                tourList = response.body();
//                mTourAdapter.setTourList(tourList);

                for (int i = 0; i < tourList.size(); i++) {
                    Log.d("retrofit_suc", tourList.get(i).getTourName());
                }
            }

            @Override
            public void onFailure(Call<List<Tour>> call, Throwable t) {
                // Xử lý lỗi
                Log.d("retrofit_error", t.toString());
            }
        });
    }

    protected void apiVehicle() {
        API apiService = RetrofitClient.getRetrofitLogin().create(API.class);
        Call<List<Vehicle>> call = apiService.getVehicles();
        call.enqueue(new Callback<List<Vehicle>>() {
            @Override
            public void onResponse(Call<List<Vehicle>> call, Response<List<Vehicle>> response) {
                vehicleList = response.body();
//                vehicleAdapter.setVehicleList(vehicleList);

                for (int i = 0; i < vehicleList.size(); i++) {
                    Log.d("retrofit_suc", vehicleList.get(i).getModel());
                }
            }

            @Override
            public void onFailure(Call<List<Vehicle>> call, Throwable t) {
                // Xử lý lỗi
                Log.d("retrofit_error", t.toString());
            }
        });
    }

    protected int checkSizeList() {
        int size1 = hotelList.size();
        int size2 = tourList.size();
        int size3 = vehicleList.size();

        if (size1 >= size2 && size1 >= size3) {
            return size1;
        } else if (size2 >= size1 && size2 >= size3) {
            return size2;
        } else {
            return size3;
        }
    }

}
