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
import com.example.ltdd_finalproject.models.Place;

import java.util.List;

public class sPlaceAdapter extends RecyclerView.Adapter<sPlaceAdapter.ViewHolder> {

    private List<Place> placeList;
    Activity activity;
    public sPlaceAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setTourList(List<Place> tourList) {
        this.placeList = tourList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.staff_fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Place hotel = placeList.get(position);

        Glide.with(activity)
                .load(placeList.get(position).getImageLink())
                .apply(new RequestOptions().override(250, 250))
                .into(holder.image);

        holder.textName.setText(String.valueOf(hotel.getPlaceName()));
        holder.textPrice.setText(String.valueOf(hotel.getDec()));
    }

    @Override
    public int getItemCount() {
        return placeList == null ? 0 : placeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView textName, textPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            textName = itemView.findViewById(R.id.textName);
            textPrice = itemView.findViewById(R.id.textPrice);
        }

    }
}
