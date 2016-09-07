package com.example.alumnos.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {

    private ArrayList<Place> placesList;
    private ClickListener clickListener;
    private Context context;

    public PlaceAdapter(ArrayList<Place> places, Context context) {
        this.placesList = places;
        this.context = context;
    }

    //Implements = paso 3
    public class PlaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, description;
        public ImageView photo;

        public PlaceViewHolder(View view) {
            super(view);
            //paso 4
            view.setOnClickListener(this);
            photo = (ImageView) view.findViewById(R.id.photo);
            title = (TextView) view.findViewById(R.id.title);
            description = (TextView) view.findViewById(R.id.description);
        }

        @Override
        //Paso 5
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.onItemClick(getAdapterPosition(), view);
            }

        }
    }

    //Paso 2
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    //Paso 1
    public interface ClickListener {
        void onItemClick(int position, View v);
    }


    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.place_card, parent, false);

        return new PlaceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder holder, int position) {
        Place place = placesList.get(position);

        holder.title.setText(place.getTitle());
        holder.description.setText(place.getDescription());
        Glide.with(this.context).load(place.getPictureUrl()).into(holder.photo);

    }

    @Override
    public int getItemCount() {
        return placesList.size();
    }


}
