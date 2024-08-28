package com.example.cinerate.user.Adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.cinerate.user.CategoryItem;
import com.example.cinerate.user.HomePageActivity;
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
        Glide.with(context).load(movie.getPosterUrl()).into(holder.itemImage);

        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    Intent i = new Intent(context, MovieDetails.class);
                    i.putExtra("movieId", moviesList.get(adapterPosition).getId());
                    i.putExtra("movieName", moviesList.get(adapterPosition).getTitle());
                    i.putExtra("poster_url", moviesList.get(adapterPosition).getPosterUrl());
                    i.putExtra("trailerUrl", moviesList.get(adapterPosition).getTrailerUrl());
                    context.startActivity(i);
                }
            }
        });
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
