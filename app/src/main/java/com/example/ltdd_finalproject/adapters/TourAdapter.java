package com.example.ltdd_finalproject.adapters;

import android.app.Activity;
import android.content.Context;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.TourViewHolder> {
    private static final String TAG = "TourAdapter";
    Activity activity;
    private List<Tour> tourList;
    private List<Tour> filteredList;
    private OnItemClickListener mListener;
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;

    public interface OnItemClickListener {
        void onItemClick(Tour tour);
    }

    // Constructor and other methods

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    public void setTourList(List<Tour> tourList) {
        this.tourList = tourList;
        this.filteredList = new ArrayList<>(tourList);
        notifyDataSetChanged();
    }
    public TourAdapter(Context context, List<Tour> datas) {
        mContext = context;
        tourList = datas;
        filteredList = new ArrayList<>(datas);
        mLayoutInflater = LayoutInflater.from(context);
        this.activity = (Activity) context;
    }

    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.tour_item, parent, false);
        return new TourViewHolder(view);
    }

    public void filter(String query) {
        query = query.toLowerCase().trim();

        filteredList.clear();

        if (query.length() == 0) {
            filteredList.addAll(tourList);
        } else {
            for (Tour tour : tourList) {
                if (tour.getTourName().toLowerCase().contains(query) ||
                        tour.getPlaceGo().toLowerCase().contains(query)) {
                    filteredList.add(tour);
                }
            }
        }

        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull TourViewHolder holder, int position) {
        Tour tour = filteredList.get(position);
        if (tour == null) {
            return;
        }


        Glide.with(activity)
                .load(tourList.get(position).getImageLink())
                .apply(new RequestOptions().override(250, 250))
                .into(holder.imageViewTour);

        holder.textViewNgayDi.setText(String.valueOf(tour.getDateGo()));
        holder.textViewPrice.setText(tour.getPrice() + " vnd");
        holder.textViewNgayDi.setText(String.valueOf( tour.getDateGo()));
        holder.textViewSoNguoi.setText("size: " + tour.getNumPerson());
        holder.textViewTourName.setText(String.valueOf(tour.getTourName()));
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public class TourViewHolder extends RecyclerView.ViewHolder implements Serializable {
        private ImageView imageViewTour;
        private TextView textViewTourName, textViewNgayDi, textViewPrice, textViewSoNguoi;

        public TourViewHolder(@NonNull View itemView) {
            super(itemView);
            anhXa();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getBindingAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            // Call onItemClick() method of the interface with the clicked tour object
                            mListener.onItemClick(tourList.get(position));
                        }
                    }
                }
            });
        }

        public void anhXa() {
            imageViewTour = itemView.findViewById(R.id.imageViewTour);
            textViewTourName = itemView.findViewById(R.id.textViewTourName);
            textViewNgayDi = itemView.findViewById(R.id.textViewNgayDi);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewSoNguoi = itemView.findViewById(R.id.textViewSoNguoi);
            textViewNgayDi = itemView.findViewById(R.id.textViewNoiDi);
        }
    }
}