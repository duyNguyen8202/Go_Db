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
import com.example.ltdd_finalproject.models.Booking;
import com.example.ltdd_finalproject.models.Hotel;

import java.util.List;

public class sBookingAdapter extends RecyclerView.Adapter<sBookingAdapter.ViewHolder> {

    private List<Booking> bookingList;
    Activity activity;

    public sBookingAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setTourList(List<Booking> bookingList) {
        this.bookingList = bookingList;
        notifyDataSetChanged();
    }

    @Override
    public sBookingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.staff_fragment_booking, parent, false);
        return new sBookingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull sBookingAdapter.ViewHolder holder, int position) {
        Booking booking = bookingList.get(position);

        holder.txtBookingId.setText(String.valueOf(booking.getBookingId()));
        holder.txtBookingType.setText(String.valueOf(booking.getBookingType()));
        holder.txtCustomerId.setText(String.valueOf(booking.getCustomerId()));
        holder.txtDate.setText(String.valueOf(booking.getBookingDate()));
    }

    @Override
    public int getItemCount() {
        return bookingList == null ? 0 : bookingList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtBookingId, txtBookingType, txtCustomerId, txtDate;

        public ViewHolder(View itemView) {
            super(itemView);
            txtBookingId = itemView.findViewById(R.id.txtBookingId);
            txtBookingType = itemView.findViewById(R.id.txtBookingType);
            txtCustomerId = itemView.findViewById(R.id.txtCustomerId);
            txtDate = itemView.findViewById(R.id.txtDate);
        }

    }
}