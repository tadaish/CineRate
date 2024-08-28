package com.example.cinerate.admin.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.cinerate.R;
import com.example.cinerate.admin.AdminHomeActivity;
import com.example.cinerate.models.Genre;
import com.example.cinerate.models.Language;
import com.example.cinerate.models.Movie;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailFragment extends Fragment {
    private boolean isEditMode = false;
    private String movieTitle, movieDes, movieDirector, movieCast, trailerUrl, posterUrl, genName, langName;
    private int moviePosition, movieId, genId, langId, releaseYear;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextInputEditText txtMovieTitle = view.findViewById(R.id.txtMovieTitle);
        TextInputEditText txtMovieDes = view.findViewById(R.id.txtMovieDes);
        TextInputEditText txtMovieDirector = view.findViewById(R.id.txtMovieDirector);
        TextInputEditText txtMovieCast = view.findViewById(R.id.txtMovieCast);
        TextInputEditText txtMovieTrailer = view.findViewById(R.id.txtMovieTrailer);
        TextInputEditText txtMoviePoster = view.findViewById(R.id.txtMoviePoster);
        TextInputEditText txtMovieYear = view.findViewById(R.id.txtMovieYear);
        AutoCompleteTextView movieGenDropDown = view.findViewById(R.id.movieGenDropDown);
        AutoCompleteTextView movieLangDropDown = view.findViewById(R.id.movieLangDropDown);
        MaterialButton saveMovieBtn = view.findViewById(R.id.saveMovieBtn);

        List<Genre> genList = AdminHomeActivity.genreDAO.getAllGenres();
        List<String> genNameList = new ArrayList<>();

        List<Language> langList = AdminHomeActivity.languageDAO.getAllLanguages();
        List<String> langNameList = new ArrayList<>();

        for (Genre gen : genList){
            genNameList.add(gen.getName());
        }

        for (Language lang : langList){
            langNameList.add(lang.getName());
        }

        ArrayAdapter<String> genAdapter =  new ArrayAdapter<>(this.getContext(), com.google.android.material.R.layout.support_simple_spinner_dropdown_item, genNameList);
        ArrayAdapter<String> langAdapter =  new ArrayAdapter<>(this.getContext(), com.google.android.material.R.layout.support_simple_spinner_dropdown_item, langNameList);

        movieGenDropDown.setAdapter(genAdapter);
        movieLangDropDown.setAdapter(langAdapter);


        Bundle bundle = getArguments();
        if (bundle != null) {
            movieTitle = bundle.getString("movieTitle");
            movieId = bundle.getInt("movieId");
            movieDes = bundle.getString("movieDes");
            movieDirector = bundle.getString("movieDirector");
            movieCast = bundle.getString("movieCast");
            trailerUrl = bundle.getString("trailerUrl");
            posterUrl = bundle.getString("posterUrl");
            isEditMode = bundle.getBoolean("isEditMode");
            moviePosition = bundle.getInt("moviePosition");
            releaseYear = bundle.getInt("releaseYear");
            genId = bundle.getInt("genId");
            langId = bundle.getInt("langId");
        }


        if (isEditMode) {
            Genre g = AdminHomeActivity.genreDAO.getGenreById(genId);
            Language l = AdminHomeActivity.languageDAO.getLangById(langId);
            genName = g.getName();
            langName = l.getName();

            txtMovieTitle.setText(movieTitle);
            txtMovieDes.setText(movieDes);
            txtMovieDirector.setText(movieDirector);
            txtMovieCast.setText(movieCast);
            txtMovieTrailer.setText(trailerUrl);
            txtMoviePoster.setText(posterUrl);
            txtMovieYear.setText(String.valueOf(releaseYear));
            movieGenDropDown.setText(genName, false);
            movieLangDropDown.setText(langName, false);
            saveMovieBtn.setText("Sửa");
        }else {
            saveMovieBtn.setText("Thêm");
        }

        movieGenDropDown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                genName = movieGenDropDown.getText().toString();
                genId = AdminHomeActivity.genreDAO.getGenIdByName(genName);

            }
        });

        movieLangDropDown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                langName = movieLangDropDown.getText().toString();
                langId = AdminHomeActivity.languageDAO.getLangIdByName(langName);
            }
        });

        saveMovieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieTitle = txtMovieTitle.getText().toString();
                movieDes = txtMovieDes.getText().toString();
                movieDirector = txtMovieDirector.getText().toString();
                movieCast = txtMovieCast.getText().toString();
                releaseYear = Integer.valueOf(txtMovieYear.getText().toString());
                trailerUrl = txtMovieTrailer.getText().toString();
                posterUrl = txtMoviePoster.getText().toString();
                if(isEditMode){
                    Movie m = new Movie(movieId, movieTitle, movieDes, releaseYear, movieDirector, posterUrl, movieCast, trailerUrl, langId, genId);

                    long rowsAffected = AdminHomeActivity.movieDAO.updateMovie(m);
                    if(rowsAffected !=0){
                        Toast.makeText(getContext(), "Sửa thành công!", Toast.LENGTH_SHORT).show();
                        MovieFragment.movieList.set(moviePosition, m);
                        MovieFragment.adapter.notifyItemChanged(moviePosition);
                    }else {
                        Toast.makeText(getContext(), "Lỗi!" + movieId, Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Movie m =  new Movie(movieTitle, movieDes, releaseYear, movieDirector, posterUrl, movieCast, trailerUrl, langId, genId);
                    long result = AdminHomeActivity.movieDAO.addMovie(m);
                    if(result != -1){
                        Toast.makeText(getContext(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
                        MovieFragment.movieList.add(m);
                        MovieFragment.adapter.notifyItemInserted(MovieFragment.movieList.size() - 1);
                    }else {
                        Toast.makeText(getContext(), "Lỗi!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}