package com.example.ltdd_finalproject.adapters.staffAdapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.models.Hotel;

import java.util.List;

public class sHotelAdapter extends RecyclerView.Adapter<sHotelAdapter.ViewHolder> {

    private List<Hotel> hotelList;
    Activity activity;

    public sHotelAdapter(Activity activity) {
        this.activity = activity;
    }
    public void setTourList(List<Hotel> tourList) {
        this.hotelList = tourList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.staff_fragment_booking, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hotel hotel = hotelList.get(position);

        Glide.with(activity)
                .load(hotelList.get(position).getImageLink())
                .apply(new RequestOptions().override(250, 250))
                .into(holder.image);

        holder.textName.setText(String.valueOf(hotel.getHotelName()));
        holder.textPrice.setText(String.valueOf(hotel.getProvince()));

    }

    @Override
    public int getItemCount() {
        return hotelList == null ? 0 : hotelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

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
