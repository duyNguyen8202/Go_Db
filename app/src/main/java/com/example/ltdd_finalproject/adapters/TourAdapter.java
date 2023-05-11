package com.example.ltdd_finalproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.models.Tour;

import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.TourViewHolder>{
    private List<Tour> tourList;
    public void setData(List<Tour> list)
    {
        this.tourList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tour_item,parent,false);
        return new TourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TourViewHolder holder, int position) {
        Tour tour = tourList.get(position);
        if (tour==null){
            return;
        }
        //holder.imageViewTour.setImageResource(tour.getImageLink());
        holder.textViewNgayDi.setText(tour.getDateGo().toString());
        holder.textViewPrice.setText(tour.getPrice().toString());
        holder.textViewNoiDi.setText(tour.getPlaceGo());
        holder.textViewSoNguoi.setText(tour.getNumPerson());
        holder.textViewTourName.setText(tour.getTourName());
    }

    @Override
    public int getItemCount() {
        if (tourList != null){
            return tourList.size();
        }
        return 0;
    }

    public class TourViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imageViewTour;
        private TextView textViewTourName,textViewNgayDi,textViewPrice,textViewSoNguoi,textViewNoiDi;
        public TourViewHolder(@NonNull View itemView) {
            super(itemView);
            anhXa();
        }
        public void anhXa(){
            imageViewTour = itemView.findViewById(R.id.imageViewTour);
            textViewTourName = itemView.findViewById(R.id.textViewTourName);
            textViewNgayDi = itemView.findViewById(R.id.textViewNgayDi);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewSoNguoi = itemView.findViewById(R.id.textViewSoNguoi);
            textViewNoiDi = itemView.findViewById(R.id.textViewNoiDi);
        }
    }
}
