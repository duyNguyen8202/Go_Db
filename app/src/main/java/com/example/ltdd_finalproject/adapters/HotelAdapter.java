package com.example.ltdd_finalproject.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.models.Hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelAdapter extends BaseAdapter {
    private Activity activity;
    private final Context mContext;
    private List<Hotel> filteredList;
    private final int layout;
    private List<Hotel> hotelList;

    public HotelAdapter(Context context, List<Hotel> data, int layout) {
        this.mContext = context;
        this.hotelList = data;
        this.filteredList = new ArrayList<>(data);
        this.layout = layout;
        this.activity = (Activity) context;
    }

    public void setHotelList(List<Hotel> tourList) {
        this.hotelList = tourList;
        this.filteredList = new ArrayList<>(tourList);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (filteredList != null) {
            return filteredList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return filteredList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Hotel hotel = filteredList.get(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)
                    mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder = new ViewHolder();
            viewHolder.textViewHotelName = convertView.findViewById(R.id.textViewHotelName);
            viewHolder.textViewProvince = convertView.findViewById(R.id.textViewProvinceHotel);
            viewHolder.textViewDiaChi = convertView.findViewById(R.id.textViewDiaChiHotel);
            viewHolder.imageViewHotel = convertView.findViewById(R.id.imageViewHotel);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textViewHotelName.setText(hotel.getHotelName());
        viewHolder.textViewProvince.setText(hotel.getProvince());
        viewHolder.textViewDiaChi.setText(hotel.getHotelAddress());

        Glide.with(activity)
                .load(hotelList.get(position).getImageLink())
                .apply(new RequestOptions().override(250, 250))
                .into(viewHolder.imageViewHotel);

        return convertView;
    }

    public void filter(String query) {
        query = query.toLowerCase().trim();

        filteredList.clear();

        if (query.length() == 0) {
            filteredList.addAll(hotelList);
        } else {
            for (Hotel hotel : hotelList) {
                if (hotel.getHotelName().toLowerCase().contains(query)) {
                    filteredList.add(hotel);
                }
                if (hotel.getProvince().toLowerCase().contains(query)) {
                    filteredList.add(hotel);
                }
                if (hotel.getHotelAddress().toLowerCase().contains(query)) {
                    filteredList.add(hotel);
                }
            }
        }

        notifyDataSetChanged();
    }

    public static class ViewHolder {
        TextView textViewHotelName, textViewProvince, textViewDiaChi;
        ImageView imageViewHotel;
    }
}

