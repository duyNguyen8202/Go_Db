package com.example.ltdd_finalproject.adapters.staffAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.models.Hotel;
import com.example.ltdd_finalproject.models.Vehicle;

import java.util.List;

public class sVehicleAdapter extends RecyclerView.Adapter<sVehicleAdapter.ViewHolder> {

    private List<Vehicle> tourHotel;

    public void setTourList(List<Vehicle> tourList) {
        this.tourHotel = tourList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.staff_fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Vehicle hotel = tourHotel.get(position);
//        holder.imageViewTour.setImageResource(tour.getImageLink());
        holder.textName.setText(String.valueOf(hotel.getModel()));
        holder.textPrice.setText(String.valueOf(hotel.getRentalRate()));
    }

    @Override
    public int getItemCount() {
        return tourHotel == null ? 0 : tourHotel.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView textName, textPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            textName = itemView.findViewById(R.id.textName);
            textPrice = itemView.findViewById(R.id.textPrice);
        }

    }
}
