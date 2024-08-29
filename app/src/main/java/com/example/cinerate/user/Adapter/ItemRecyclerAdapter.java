package com.example.cinerate.user.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cinerate.R;
import com.example.cinerate.daos.MovieDAO;
import com.example.cinerate.models.Movie;
import com.example.cinerate.user.MovieDetails;

import java.util.List;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ItemViewHolder> {

    Context context;
    List<Movie> moviesList;
    public static MovieDAO movieDAO;


    public ItemRecyclerAdapter(Context context, List<Movie> moviesList) {
        this.context = context;
        this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.cat_recycler_row_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder,final int position) {
        Movie movie = moviesList.get(position);
        Glide.with(context).load(movie.getPosterUrl()).centerInside().into(holder.itemImage);

        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    Log.d("MovieID", "Movie ID being passed: " + moviesList.get(adapterPosition).getId());

                    Intent i = new Intent(context, MovieDetails.class);
                    i.putExtra("movieId", moviesList.get(position).getId());
                    i.putExtra("movieName", moviesList.get(position).getTitle());
                    i.putExtra("movieDes", moviesList.get(position).getDescription());
                    i.putExtra("movieCast", moviesList.get(position).getMainCast());
                    i.putExtra("movieDirector", moviesList.get(position).getDirector());
                    i.putExtra("releaseYear", moviesList.get(position).getRelease_year());
                    i.putExtra("poster_url", moviesList.get(position).getPosterUrl());
                    i.putExtra("trailerUrl", moviesList.get(position).getTrailerUrl());
                    context.startActivity(i);
                }

            }

        });
        Log.d("MovieID", "Movie ID being passed: " + moviesList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public static final class ItemViewHolder extends  RecyclerView.ViewHolder{
        ImageView itemImage;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.item_image);
        }
    }
}
