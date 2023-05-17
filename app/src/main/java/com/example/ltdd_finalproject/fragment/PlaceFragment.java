package com.example.ltdd_finalproject.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.adapters.PlaceAdapter;
import com.example.ltdd_finalproject.models.Place;

import java.util.List;

//public class PlaceFragment extends Fragment {
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_doanh_thu, container, false);
//        // Khởi tạo các thành phần giao diện cho fragment này
//        return view;
//    }
//}
public class PlaceFragment extends Fragment {

    private final List<Place> placeList;
    private RecyclerView recyclerView;
    private PlaceAdapter adapter;

    public PlaceFragment(List<Place> placeList) {
        this.placeList = placeList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doanh_thu, container, false);

        // Thiết lập RecyclerView
        recyclerView = view.findViewById(R.id.doanh_thu_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        adapter = new PlaceAdapter(requireContext(), placeList);
//        recyclerView.setAdapter(adapter);

        return view;
    }

}
