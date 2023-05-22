package com.example.ltdd_finalproject.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.models.Hotel;
import com.example.ltdd_finalproject.models.Tour;
import com.example.ltdd_finalproject.models.Vehicle;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity activity;
    private final Context mContext;
    private final List<Object> objectList;
    private static final int VEHICLE = 0;
    private static final int HOTEL = 1;
    private static final int TOUR = 2;

    public CustomAdapter(Context context, List<Object> objects) {
        this.mContext = context;
        this.objectList = objects;
        this.activity = (Activity)  context;
    }

    public class VehicleViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtviewModel;
        private final TextView txtLicense;
        private ImageView imageViewVehicle;

        public VehicleViewHolder(View view) {
            super(view);
            txtviewModel = view.findViewById(R.id.txtviewModel);
            txtLicense = view.findViewById(R.id.txtLicense);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Vehicle vehicle = (Vehicle) objectList.get(getAdapterPosition());
                    Toast.makeText(mContext, objectList.get(getAdapterPosition()).toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public class HotelViewHolder extends RecyclerView.ViewHolder {
        TextView textViewHotelName, textViewProvince, textViewDiaChi;
        ImageView imageViewHotel;

        public HotelViewHolder(View view) {
            super(view);
            textViewHotelName = view.findViewById(R.id.textViewHotelName);
            textViewProvince = view.findViewById(R.id.textViewProvinceHotel);
            textViewDiaChi = view.findViewById(R.id.textViewDiaChiHotel);
            imageViewHotel = view.findViewById(R.id.imageViewHotel);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Hotel hotel = (Hotel) objectList.get(getAdapterPosition());
                    Toast.makeText(mContext, objectList.get(getAdapterPosition()).toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public class TourViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageViewTour;
        private final TextView textViewTourName;
        private final TextView textViewNgayDi;
        private final TextView textViewPrice;
        private final TextView textViewSoNguoi;
        private final TextView textViewNoiDi;

        public TourViewHolder(View view) {
            super(view);
            textViewTourName = view.findViewById(R.id.textName);
            textViewNgayDi = view.findViewById(R.id.textViewNgayDi);
            textViewPrice = view.findViewById(R.id.textPrice);
            textViewSoNguoi = view.findViewById(R.id.textViewSoNguoi);
            textViewNoiDi = view.findViewById(R.id.textViewNoiDi);
            imageViewTour = view.findViewById(R.id.imageViewTour);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Tour tour = (Tour) objectList.get(getAdapterPosition());
                    Toast.makeText(mContext, objectList.get(getAdapterPosition()).toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        switch (viewType) {
            case VEHICLE:
                View vehicleView = layoutInflater.inflate(R.layout.vehicle_item, parent, false);
                return new VehicleViewHolder(vehicleView);
        }
        switch (viewType) {
            case HOTEL:
                View hotelView = layoutInflater.inflate(R.layout.hotel_item1, parent, false);
                return new HotelViewHolder(hotelView);
        }
        switch (viewType) {
            case TOUR:
                View tourView = layoutInflater.inflate(R.layout.tour_item, parent, false);
                return new TourViewHolder(tourView);
            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d("CustomAdapter", "Binding data to view holder at position " + position);
        switch (getItemViewType(position)) {
            case VEHICLE:
                Vehicle vehicle = (Vehicle) objectList.get(position);
                VehicleViewHolder vehicleViewHolder = (VehicleViewHolder) holder;
                vehicleViewHolder.txtviewModel.setText(vehicle.getModel());
                vehicleViewHolder.txtLicense.setText(vehicle.getLicensePlate());
                //vehicleViewHolder.imageViewVehicle.setText(objectList.get(position).toString());
                break;
            case HOTEL:
                Hotel hotel = (Hotel) objectList.get(position);
                HotelViewHolder hotelViewHolder = (HotelViewHolder) holder;
                hotelViewHolder.textViewHotelName.setText(hotel.getHotelName());
                hotelViewHolder.textViewProvince.setText(hotel.getProvince());
                hotelViewHolder.textViewDiaChi.setText(hotel.getHotelAddress());

                Glide.with(activity)
                        .load(hotel.getImageLink())
                        .apply(new RequestOptions().override(250, 250))
                        .into(hotelViewHolder.imageViewHotel);

                //hotelViewHolder.imageViewHotel.setImageResource(objectList.get(position));
                //vehicleViewHolder.imageViewVehicle.setText(objectList.get(position).toString());
                break;
            case TOUR:
                Tour tour = (Tour) objectList.get(position);
                TourViewHolder tourViewHolder = (TourViewHolder) holder;
                tourViewHolder.textViewTourName.setText(tour.getTourName());
                tourViewHolder.textViewNgayDi.setText(tour.getDateGo());
                tourViewHolder.textViewPrice.setText(tour.getPrice().toString());
                tourViewHolder.textViewSoNguoi.setText(String.valueOf(tour.getNumPerson()));
                tourViewHolder.textViewNoiDi.setText(tour.getPlaceGo());
                //tourViewHolder.imageViewTour.setImageResource((int)objectList.get(position));
                //vehicleViewHolder.imageViewVehicle.setText(objectList.get(position).toString());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (objectList.get(position) instanceof Vehicle) {
            return VEHICLE;
        } else if (objectList.get(position) instanceof Hotel) {
            return HOTEL;
        } else if (objectList.get(position) instanceof Tour) {
            return TOUR;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }
}
