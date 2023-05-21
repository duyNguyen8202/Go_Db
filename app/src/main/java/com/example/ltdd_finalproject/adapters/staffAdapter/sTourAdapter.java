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
import com.example.ltdd_finalproject.models.Tour;

import java.util.List;

public class sTourAdapter extends RecyclerView.Adapter<sTourAdapter.ViewHolder> {

    private List<Tour> tourList;
    Activity activity;

    private static OnItemClickListener listener;

    public sTourAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setTourList(List<Tour> tourList) {
        this.tourList = tourList;
        notifyDataSetChanged();
    }


    @Override
    public sTourAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.staff_fragment_item, parent, false);
        return new sTourAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull sTourAdapter.ViewHolder holder, int position) {
        Tour tour = tourList.get(position);

        Glide.with(activity)
                .load(tourList.get(position).getImageLink())
                .apply(new RequestOptions().override(250, 250))
                .into(holder.imageViewTour);

        holder.textViewTourName.setText(String.valueOf(tour.getTourName()));
        holder.textPrice.setText(String.valueOf(tour.getPrice()));
    }

    public interface OnItemClickListener {
        void onItemClick(Tour tour);
    }

    public void setOnItemClickListener(sTourAdapter.OnItemClickListener listener) {
        sTourAdapter.listener = listener;
    }



    @Override
    public int getItemCount() {
        return tourList == null ? 0 : tourList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageViewTour;
        private final TextView textViewTourName, textPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewTour = itemView.findViewById(R.id.image);
            textViewTourName = itemView.findViewById(R.id.textName);
            textPrice = itemView.findViewById(R.id.textPrice);
            // Thiết lập sự kiện onclick cho view tổng thể của tour
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            Tour tour = tourList.get(position);
                            listener.onItemClick(tour);
                        }
                    }
                }
            });
        }

    }
}
