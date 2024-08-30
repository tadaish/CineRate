package com.example.cinerate.user.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.cinerate.R;
import com.example.cinerate.models.Movie;
import com.example.cinerate.user.MovieDetails;

import java.util.List;

public class MoviePagerAdapter extends PagerAdapter {

    Context context;
    List<Movie> moviesList;

    public MoviePagerAdapter(Context context, List<Movie> moviesList) {
        this.context = context;
        this.moviesList = moviesList;
    }

    @Override
    public int getCount() {
        if (moviesList == null || moviesList.isEmpty()) {
            return 0;
        }
        return moviesList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.banner_movie_layout, null);
        ImageView bannerImage = view.findViewById(R.id.banner_image);
        Movie movie = moviesList.get(position);

        Glide.with(context).load(movie.getPosterUrl()).centerInside().into(bannerImage);
        container.addView(view);

        bannerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MovieID", "Movie ID being passed: " + moviesList.get(position).getId());

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
        }) ;
        return view;

    }

}
