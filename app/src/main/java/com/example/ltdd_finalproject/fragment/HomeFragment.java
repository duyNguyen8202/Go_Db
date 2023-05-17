package com.example.ltdd_finalproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.activity.AllHotelActivity;
import com.example.ltdd_finalproject.activity.AllTourActivity;
import com.example.ltdd_finalproject.activity.AllVehicleActivity;
import com.example.ltdd_finalproject.adapters.CustomAdapter;
import com.example.ltdd_finalproject.models.Hotel;
import com.example.ltdd_finalproject.models.Tour;
import com.example.ltdd_finalproject.models.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private List<Object> mdata;
    RecyclerView recyclerView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button allTourBtn,buttonHotel;
    Button allVehicleBtn;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
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
//    protected void anhXa(){
//
//        allTourBtn=(Button) findViewById(R.id.buttonTous);
//        allVehicleBtn=(Button) findViewById(R.id.buttonVehicle);
//        buttonHotel=(Button) findViewById(R.id.buttonHotel);
//    }
//    protected  void setEvent(){
//        //Event for Tours
//        allTourBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(HomeFragment.this, AllTourActivity.class);
//                startActivity(intent);
//            }
//        });
//        allVehicleBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(HomeActivity.this, AllVehicleActivity.class);
//                startActivity(intent);
//            }
//        });
//        buttonHotel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(HomeActivity.this, AllHotelActivity.class);
//                startActivity(intent);
//            }
//        });
//
//    }
    protected void themData(){
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

    mdata.add(new Tour("1", "1", "1", "Tour 1", "Place 1", LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 5), 2, BigDecimal.valueOf(200.0), "https://example.com/image1.jpg", true));
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
    mdata.add(new Tour("2", "2", "2", "Tour 2", "Place 2", LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 5), 3, BigDecimal.valueOf(300.0), "https://example.com/image2.jpg", false));

}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        allTourBtn = view.findViewById(R.id.buttonTous);
        allVehicleBtn = view.findViewById(R.id.buttonVehicle);
        buttonHotel = view.findViewById(R.id.buttonHotel);
        recyclerView=view.findViewById(R.id.rv_multipe_view_type);
        themData();
        CustomAdapter customAdapter=new CustomAdapter(getContext(),mdata);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        allTourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AllTourActivity.class);
                startActivity(intent);
            }
        });
        allVehicleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AllVehicleActivity.class);
                startActivity(intent);
            }
        });
        buttonHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AllHotelActivity.class);
                startActivity(intent);
            }
        });

    }
}
