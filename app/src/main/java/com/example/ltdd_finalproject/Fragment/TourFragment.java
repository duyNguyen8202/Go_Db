package com.example.ltdd_finalproject.Fragment;

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
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_doanh_thu, container, false);
//        // Khởi tạo các thành phần giao diện cho fragment này
//        return view;
//    }
//private Tour tour;
//
//    public TourFragment(Tour tour) {
//        this.tour = tour;
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_tour, container, false);
//        ImageView imageView = view.findViewById(R.id.imageViewTour);
//        TextView nameTextView = view.findViewById(R.id.textViewTourName);
//        TextView dateTextView = view.findViewById(R.id.textViewNgayDi);
//        TextView priceTextView = view.findViewById(R.id.textViewPrice);
//        TextView placeTextView = view.findViewById(R.id.textViewNoiDi);
//        TextView numPersonTextView = view.findViewById(R.id.textViewSoNguoi);
//
//        Glide.with(requireContext()).load(tour.getImageLink()).into(imageView);
//        nameTextView.setText(tour.getTourName());
//        dateTextView.setText(tour.getDateGo());
//        priceTextView.setText(String.valueOf(tour.getPrice()));
//        placeTextView.setText(tour.getPlaceGo());
//        numPersonTextView.setText(String.valueOf(tour.getNumPerson()));
//
//        return view;
//    }


    private List<Tour> tourList;
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

//    @Override
//    public void onBindViewHolder(@NonNull TourAdapter.TourViewHolder holder, int position) {
//        Tour tour = tourList.get(position);
//        if (tour == null) {
//            return;
//        }
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TourFragment fragment = new TourFragment(Collections.singletonList(tour));
//                FragmentManager manager = ((AppCompatActivity) mContext).getSupportFragmentManager();
//                FragmentTransaction transaction = manager.beginTransaction();
//                transaction.replace(R.id.fragment_container_view, fragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//            }
//        });
//        holder.textViewNgayDi.setText(String.valueOf(tour.getDateGo()));
//        holder.textViewPrice.setText(String.valueOf(tour.getPrice()));
//        holder.textViewNoiDi.setText(String.valueOf(tour.getPlaceGo()));
//        holder.textViewSoNguoi.setText(String.valueOf(tour.getNumPerson()));
//        holder.textViewTourName.setText(String.valueOf(tour.getTourName()));
//    }
}

