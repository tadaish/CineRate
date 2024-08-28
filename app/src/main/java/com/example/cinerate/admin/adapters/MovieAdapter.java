package com.example.cinerate.admin.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.cinerate.R;
import com.example.cinerate.admin.AdminHomeActivity;
import com.example.cinerate.admin.fragments.GenreFragment;
import com.example.cinerate.admin.fragments.LanguageDetailFragment;
import com.example.cinerate.admin.fragments.MovieDetailFragment;
import com.example.cinerate.admin.fragments.MovieFragment;
import com.example.cinerate.models.Genre;
import com.example.cinerate.models.Language;
import com.example.cinerate.models.Movie;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHoler> {
    private List<Movie> movieList;
    private final Context context;

    public MovieAdapter(List<Movie> movieList, Context context){
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movie, parent, false);
        return new MovieAdapter.MovieViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHoler holder, int position) {
        Movie m = movieList.get(position);
        Genre g = AdminHomeActivity.genreDAO.getGenreById(m.getGenreId());
        Language lang = AdminHomeActivity.languageDAO.getLangById(m.getLanguageId());
        holder.txtMovieTitle.setText(m.getTitle());
        holder.txtMovieGen.setText(g.getName());
        holder.txtMovieLang.setText(lang.getName());

        Glide.with(holder.itemView.getContext())
                .load(m.getPosterUrl())
                .transform(new CenterCrop(), new BlurTransformation(5,2))
                .into(holder.posterImageView);

        int itemPosition = holder.getPosition();
        holder.editMovBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment movieDetailFragment = new MovieDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("isEditMode", true);
                bundle.putString("movieTitle", m.getTitle());
                bundle.putString("movieDes", m.getDescription());
                bundle.putString("movieDirector", m.getDirector());
                bundle.putString("movieCast", m.getMainCast());
                bundle.putInt("releaseYear", m.getRelease_year());
                bundle.putString("trailerUrl", m.getTrailerUrl());
                bundle.putString("posterUrl", m.getPosterUrl());
                bundle.putInt("movieId", m.getId());
                bundle.putInt("genId", m.getGenreId());
                bundle.putInt("langId", m.getLanguageId());
                bundle.putInt("moviePosition", itemPosition);
                movieDetailFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = MovieFragment.fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, movieDetailFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        holder.delMovBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminHomeActivity.movieDAO.deleteMovie(m.getId());
                movieList =  AdminHomeActivity.movieDAO.getAllMovies();
                MovieFragment.adapter.notifyItemRemoved(itemPosition);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MovieViewHoler extends RecyclerView.ViewHolder{
        public TextView txtMovieTitle, txtMovieGen, txtMovieLang;
        public FloatingActionButton editMovBtn, delMovBtn;
        public MaterialCardView movieCardView;
        public ImageView posterImageView;
        public MovieViewHoler(@NonNull View itemView){
            super(itemView);
            txtMovieTitle = itemView.findViewById(R.id.txtMovieTitle);
            txtMovieGen = itemView.findViewById(R.id.txtMovieGen);
            txtMovieLang = itemView.findViewById(R.id.txtMovieLang);
            editMovBtn = itemView.findViewById(R.id.editMovBtn);
            delMovBtn = itemView.findViewById(R.id.delMovBtn);
            movieCardView = itemView.findViewById(R.id.movieCardView);
            posterImageView = itemView.findViewById(R.id.posterImageView);
        }



    }
}
