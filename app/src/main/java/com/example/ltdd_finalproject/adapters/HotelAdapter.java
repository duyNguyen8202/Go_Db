package com.example.ltdd_finalproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.models.Hotel;

import java.util.List;

public class HotelAdapter extends
        BaseAdapter {
    private final Context mContext;

    private final int layout;
    private final List<Hotel> hotelList;
    public HotelAdapter(Context context, List<Hotel> data, int layout){
        this.mContext=context;
        this.hotelList=data;
        this.layout=layout;
    }
    @Override
    public int getCount() {
        if (hotelList != null){
            return hotelList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view==null){
            LayoutInflater inflater = (LayoutInflater)
                    mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            viewHolder = new ViewHolder();
            viewHolder.textViewHotelName = view.findViewById(R.id.textViewHotelName);
            viewHolder.textViewProvince = view.findViewById(R.id.textViewProvinceHotel);
            viewHolder.textViewDiaChi = view.findViewById(R.id.textViewDiaChiHotel);
            viewHolder.imageViewHotel = view.findViewById(R.id.imageViewHotel);
            view.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) view.getTag();
        }
        Hotel hotel = hotelList.get(i);
        viewHolder.textViewHotelName.setText(hotel.getHotelName());
        //viewHolder.textViewProvince.setText(hotel.getProvince());
        //viewHolder.textViewDiaChi.setText(hotel.getHotelAddress());
        //viewHolder.imageViewHotel.setImageResource((hotel.getImageLink()).par);
//trả về view
        return view;
    }
    private class ViewHolder{
        TextView textViewHotelName,textViewProvince,textViewDiaChi;
        ImageView imageViewHotel;
    }
}
