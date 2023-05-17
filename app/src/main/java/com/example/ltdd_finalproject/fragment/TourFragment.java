package com.example.ltdd_finalproject.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.adapters.TourAdapter;
import com.example.ltdd_finalproject.models.Tour;

import java.util.Collections;
import java.util.List;

public class TourFragment extends Fragment {

    private final List<Tour> tourList;
    private RecyclerView recyclerView;
    private TourAdapter adapter;


    public TourFragment(List<Tour> tours) {
        tourList = tours;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doanh_thu, container, false);
        // Thiết lập RecyclerView
        recyclerView = view.findViewById(R.id.doanh_thu_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TourAdapter(requireContext(), tourList);
        recyclerView.setAdapter(adapter);
        return view;
    }

}

