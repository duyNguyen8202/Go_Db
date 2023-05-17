package com.example.ltdd_finalproject.adapters.staffAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.models.Tour;

import java.util.List;

public class sTourAdapter extends RecyclerView.Adapter<sTourAdapter.ViewHolder> {

    private List<Tour> tourList;

    public void setTourList(List<Tour> tourList) {
        this.tourList = tourList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.staff_fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tour tour = tourList.get(position);
//        holder.imageViewTour.setImageResource(tour.getImageLink());
        holder.textViewTourName.setText(String.valueOf(tour.getTourName()));
        holder.textPrice.setText(String.valueOf(tour.getPrice()));
    }

    @Override
    public int getItemCount() {
        return tourList == null ? 0 : tourList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageViewTour;
        private final TextView textViewTourName, textPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewTour = itemView.findViewById(R.id.image);
            textViewTourName = itemView.findViewById(R.id.textName);
            textPrice = itemView.findViewById(R.id.textPrice);
        }

    }
}
