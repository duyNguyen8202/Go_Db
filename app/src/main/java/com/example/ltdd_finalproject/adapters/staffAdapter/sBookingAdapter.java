package com.example.ltdd_finalproject.adapters.staffAdapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.models.Booking;

import java.util.List;

public class sBookingAdapter extends RecyclerView.Adapter<sBookingAdapter.ViewHolder> {

    private List<Booking> bookingList;
    Activity activity;
    private static OnItemClickListener listener;

    public sBookingAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setBookingList(List<Booking> bookingList) {
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

        holder.txtBookingId.setText("BookingId: " + booking.getBookingId());
        holder.txtBookingType.setText("BookingType: " + booking.getBookingType());
        holder.txtCustomerId.setText("CustomerId: " + booking.getCustomerId());
        holder.txtDate.setText("Date: " + booking.getBookingDate());
    }

    public interface OnItemClickListener {
        void onItemClick(Booking booking);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public int getItemCount() {
        return bookingList == null ? 0 : bookingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtBookingId, txtBookingType, txtCustomerId, txtDate;

        public ViewHolder(View itemView) {
            super(itemView);
            txtBookingId = itemView.findViewById(R.id.txtBookingId);
            txtBookingType = itemView.findViewById(R.id.txtBookingType);
            txtCustomerId = itemView.findViewById(R.id.txtCustomerId);
            txtDate = itemView.findViewById(R.id.txtDate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getBindingAdapterPosition();
                        if (position != RecyclerView.NO_POSITION && listener != null && !bookingList.isEmpty()) {
                            Booking booking = bookingList.get(position);
                            listener.onItemClick(booking);
                        }
                    }
                }
            });
        }
    }
}