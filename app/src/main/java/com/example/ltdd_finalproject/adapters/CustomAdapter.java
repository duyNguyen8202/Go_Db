package com.example.ltdd_finalproject.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.models.Hotel;
import com.example.ltdd_finalproject.models.Tour;
import com.example.ltdd_finalproject.models.Vehicle;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Object> objectList;
    private static final int VEHICLE=0;
    private static final int HOTEL=1;
    private static final int TOUR=2;

    public CustomAdapter(Context context,List<Object> objects)
    {
        this.mContext=context;
        this.objectList=objects;
    }
    public class VehicleViewHolder extends RecyclerView.ViewHolder{
        private TextView txtviewModel,txtLicense;
        private ImageView imageViewVehicle;
        public VehicleViewHolder(View view){
            super(view);
            txtviewModel=(TextView) view.findViewById(R.id.txtviewModel);
            txtLicense=(TextView) view.findViewById(R.id.txtLicense);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Vehicle vehicle=(Vehicle) objectList.get(getAdapterPosition());
                    Toast.makeText(mContext,objectList.get(getAdapterPosition()).toString(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public class HotelViewHolder extends RecyclerView.ViewHolder{
        TextView textViewHotelName,textViewProvince,textViewDiaChi;
        ImageView imageViewHotel;
        public HotelViewHolder(View view){
            super(view);
            textViewHotelName=(TextView) view.findViewById(R.id.textViewTourName);
            textViewProvince=(TextView) view.findViewById(R.id.textViewProvince);
            textViewDiaChi=(TextView) view.findViewById(R.id.textViewDiaChiHotel);
            imageViewHotel=(ImageView) view.findViewById(R.id.imageViewHotel);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Hotel hotel=(Hotel) objectList.get(getAdapterPosition());
                    Toast.makeText(mContext,objectList.get(getAdapterPosition()).toString(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public class TourViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageViewTour;
        private TextView textViewTourName,textViewNgayDi,textViewPrice,textViewSoNguoi,textViewNoiDi;
        public TourViewHolder(View view){
            super(view);
            textViewTourName=(TextView) view.findViewById(R.id.textViewTourName);
            textViewNgayDi=(TextView) view.findViewById(R.id.textViewNgayDi);
            textViewPrice=(TextView) view.findViewById(R.id.textViewPrice);
            textViewSoNguoi=(TextView) view.findViewById(R.id.textViewSoNguoi);
            textViewNoiDi=(TextView) view.findViewById(R.id.textViewNoiDi);
            imageViewTour=(ImageView) view.findViewById(R.id.imageViewTour);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Tour tour=(Tour) objectList.get(getAdapterPosition());
                    Toast.makeText(mContext,objectList.get(getAdapterPosition()).toString(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        switch (viewType){
            case VEHICLE:
                View vehicleView= layoutInflater.inflate(R.layout.vehicle_item,parent,false);
                return new VehicleViewHolder(vehicleView);
        }
        switch (viewType){
            case HOTEL:
                View hotelView= layoutInflater.inflate(R.layout.hotel_item1,parent,false);
                return new HotelViewHolder(hotelView);
        }
        switch (viewType){
            case TOUR:
                View tourView= layoutInflater.inflate(R.layout.tour_item,parent,false);
                return new TourViewHolder(tourView);
            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case VEHICLE:
                VehicleViewHolder vehicleViewHolder=(VehicleViewHolder) holder;
                vehicleViewHolder.txtLicense.setText(objectList.get(position).toString());
                vehicleViewHolder.txtviewModel.setText(objectList.get(position).toString());
                //vehicleViewHolder.imageViewVehicle.setText(objectList.get(position).toString());
                break;
            case HOTEL:
                HotelViewHolder hotelViewHolder=(HotelViewHolder) holder;
                hotelViewHolder.textViewHotelName.setText(objectList.get(position).toString());
                hotelViewHolder.textViewDiaChi.setText(objectList.get(position).toString());
                hotelViewHolder.textViewProvince.setText(objectList.get(position).toString());
                hotelViewHolder.imageViewHotel.setImageResource((int)objectList.get(position));

                //vehicleViewHolder.imageViewVehicle.setText(objectList.get(position).toString());
                break;
            case TOUR:
                TourViewHolder tourViewHolder=(TourViewHolder) holder;
                tourViewHolder.textViewTourName.setText(objectList.get(position).toString());
                tourViewHolder.textViewPrice.setText(objectList.get(position).toString());
                tourViewHolder.textViewNgayDi.setText(objectList.get(position).toString());
                tourViewHolder.textViewNoiDi.setText(objectList.get(position).toString());
                tourViewHolder.textViewSoNguoi.setText(objectList.get(position).toString());
                tourViewHolder.imageViewTour.setImageResource((int)objectList.get(position));
                //vehicleViewHolder.imageViewVehicle.setText(objectList.get(position).toString());
                break;
        }
    }
    @Override
    public int getItemViewType(int position){
        if(objectList.get(position)instanceof Vehicle)
        {return VEHICLE;}
        else if(objectList.get(position)instanceof Hotel)
        {return HOTEL;}
        else if(objectList.get(position)instanceof Tour)
        {return TOUR;}
        return -1;
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }
}