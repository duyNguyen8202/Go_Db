package com.example.ltdd_finalproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.models.Booking;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class BookingAdapter extends ArrayAdapter<Booking>{
    private Context context;
    private List<Booking> bookings;

    public BookingAdapter(Context context, List<Booking> bookings) {
        super(context, 0, bookings);
        this.context = context;
        this.bookings = bookings;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_booking, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.bookingIdTextView = view.findViewById(R.id.bookingIdTextView);
            viewHolder.customerIdTextView = view.findViewById(R.id.customerIdTextView);
            viewHolder.staffIdTextView = view.findViewById(R.id.staffIdTextView);
            viewHolder.vehicleIdTextView = view.findViewById(R.id.vehicleIdTextView);
            viewHolder.tourIdTextView = view.findViewById(R.id.tourIdTextView);
            viewHolder.hotelIdTextView = view.findViewById(R.id.hotelIdTextView);
            viewHolder.bookingDateTextView = view.findViewById(R.id.bookingDateTextView);
            viewHolder.bookingTypeTextView = view.findViewById(R.id.bookingTypeTextView);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Booking booking = bookings.get(position);

        viewHolder.bookingIdTextView.setText("Booking ID: " + booking.getBookingId());
        viewHolder.customerIdTextView.setText("Customer ID: " + booking.getCustomerId());
        viewHolder.staffIdTextView.setText("Staff ID: " + booking.getStaffId());

        if (booking.getTourId() == null && booking.getHotelId() == null) {
            viewHolder.vehicleIdTextView.setVisibility(View.VISIBLE);
            viewHolder.tourIdTextView.setVisibility(View.GONE);
            viewHolder.hotelIdTextView.setVisibility(View.GONE);

            viewHolder.vehicleIdTextView.setText("Vehicle ID: " + booking.getVehicleId());
        } else {
            viewHolder.vehicleIdTextView.setVisibility(View.GONE);
            viewHolder.tourIdTextView.setVisibility(View.VISIBLE);
            viewHolder.hotelIdTextView.setVisibility(View.VISIBLE);

            viewHolder.tourIdTextView.setText("Tour ID: " + booking.getTourId());
            viewHolder.hotelIdTextView.setText("Hotel ID: " + booking.getHotelId());
        }

        viewHolder.bookingDateTextView.setText("Booking Date: " + booking.getBookingDate());
        viewHolder.bookingTypeTextView.setText("Booking Type: " + booking.getBookingType());

        return view;
    }

    private static class ViewHolder {
        TextView bookingIdTextView;
        TextView customerIdTextView;
        TextView staffIdTextView;
        TextView vehicleIdTextView;
        TextView tourIdTextView;
        TextView hotelIdTextView;
        TextView bookingDateTextView;
        TextView bookingTypeTextView;
    }
}