package com.example.ltdd_finalproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.models.Tour;

import java.util.ArrayList;
import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.TourViewHolder> {
    private static final String TAG = "TourAdapter";
    private List<Tour> tourList;
    private List<Tour> filteredList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public TourAdapter(Context context, List<Tour> datas) {
        mContext = context;
        tourList = datas;
        filteredList = new ArrayList<>(datas);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.tour_item, parent, false);
        return new TourViewHolder(view);
    }

    public void filter(String query) {
        query = query.toLowerCase().trim();

        filteredList.clear();

        if (query.length() == 0) {
            filteredList.addAll(tourList);
        } else {
            for (Tour tour : tourList) {
                if (tour.getTourName().toLowerCase().contains(query) ||
                        tour.getPlaceGo().toLowerCase().contains(query)) {
                    filteredList.add(tour);
                }
            }
        }

        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull TourViewHolder holder, int position) {
        Tour tour = filteredList.get(position);
        if (tour == null) {
            return;
        }
        //holder.imageViewTour.setImageResource(tour.getImageLink());
        holder.textViewNgayDi.setText(String.valueOf(tour.getDateGo()));
        holder.textViewPrice.setText(String.valueOf(tour.getPrice()));
        holder.textViewNoiDi.setText(String.valueOf(tour.getPlaceGo()));
        holder.textViewSoNguoi.setText(String.valueOf(tour.getNumPerson()));
        holder.textViewTourName.setText(String.valueOf(tour.getTourName()));
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public class TourViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewTour;
        private TextView textViewTourName, textViewNgayDi, textViewPrice, textViewSoNguoi, textViewNoiDi;

        public TourViewHolder(@NonNull View itemView) {
            super(itemView);
            anhXa();
        }

        public void anhXa() {
            imageViewTour = itemView.findViewById(R.id.imageViewTour);
            textViewTourName = itemView.findViewById(R.id.textViewTourName);
            textViewNgayDi = itemView.findViewById(R.id.textViewNgayDi);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewSoNguoi = itemView.findViewById(R.id.textViewSoNguoi);
            textViewNoiDi = itemView.findViewById(R.id.textViewNoiDi);
        }
    }
}
