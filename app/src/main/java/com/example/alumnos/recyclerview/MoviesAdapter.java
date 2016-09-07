package com.example.alumnos.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private ArrayList<Movie> moviesList;
    private ClickListener clickListener;

    //Implements = paso 3
    public class MoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, year, likes, dislikes;

        public MoviesViewHolder(View view) {
            super(view);
            //paso 4
            view.setOnClickListener(this);
            title = (TextView) view.findViewById(R.id.titulo);
            year = (TextView) view.findViewById(R.id.year);
            likes = (TextView) view.findViewById(R.id.likes);
            dislikes = (TextView) view.findViewById(R.id.dislikes);
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

    public MoviesAdapter(ArrayList<Movie> movies) {
        this.moviesList = movies;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_row, parent, false);

        return new MoviesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        Movie movie = moviesList.get(position);

        holder.title.setText(movie.getTitle());
        holder.year.setText(movie.getYear());
        holder.likes.setText(movie.getLikes() + "%");
        holder.dislikes.setText(movie.getDislikes() + "%");
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }



}
