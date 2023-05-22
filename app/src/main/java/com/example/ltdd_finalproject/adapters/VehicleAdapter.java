package com.example.ltdd_finalproject.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.models.Hotel;
import com.example.ltdd_finalproject.models.Tour;
import com.example.ltdd_finalproject.models.Vehicle;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.models.Tour;
import com.example.ltdd_finalproject.models.Vehicle;

import java.util.List;

public class VehicleAdapter extends BaseAdapter implements Filterable {
    private final Context mContext;
    Activity activity;
    private final int layout;
    private final LayoutInflater mLayoutInflater;
    private List<Vehicle> vehicleList;
    private List<Vehicle> filteredList;

    public VehicleAdapter(Context context, List<Vehicle> datas, int layout) {
        this.mContext = context;
        this.vehicleList = datas;
        this.filteredList = new ArrayList<>(datas);
        this.layout = layout;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.activity = (Activity) context;
    }

    @Override
    public int getCount() {
        if (filteredList != null) {
            return filteredList.size();
        }
        return 0;
    }
    public void setVehicleList(List<Vehicle> tourList) {
        this.vehicleList = tourList;
        this.filteredList = new ArrayList<>(tourList);
        notifyDataSetChanged();
    }

    @Override
    public Object getItem(int i) {
        return filteredList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = mLayoutInflater.inflate(layout, null);
            viewHolder = new ViewHolder();
            viewHolder.txtviewModel = view.findViewById(R.id.txtviewModel);
            viewHolder.txtLicense = view.findViewById(R.id.txtLicense);
//            viewHolder.imageViewVehicle = view.findViewById(R.id.imageViewVehicle);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Vehicle vehicle = filteredList.get(i);
        viewHolder.txtviewModel.setText(vehicle.getModel());
        viewHolder.txtLicense.setText(vehicle.getLicensePlate());

//        Glide.with(activity)
//                .load(vehicleList.get(i).get)
//                .apply(new RequestOptions().override(250, 250))
//                .into(viewHolder.imageViewVehicle);
        return view;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String query = charSequence.toString().toLowerCase().trim();
                FilterResults results = new FilterResults();
                List<Vehicle> filteredVehicles = new ArrayList<>();
                if (query.length() == 0) {
                    filteredVehicles.addAll(vehicleList);
                } else {
                    for (Vehicle vehicle : vehicleList) {
                        if (vehicle.getModel().toLowerCase().contains(query) ||
                                vehicle.getLicensePlate().toLowerCase().contains(query)) {
                            filteredVehicles.add(vehicle);
                        }
                    }
                }
                results.values = filteredVehicles;
                results.count = filteredVehicles.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredList = (List<Vehicle>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    private class ViewHolder {
        TextView txtviewModel, txtLicense;
        ImageView imageViewVehicle;
    }
}
