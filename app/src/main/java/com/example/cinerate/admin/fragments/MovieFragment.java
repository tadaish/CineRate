package com.example.cinerate.admin.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.SearchView;

import com.example.cinerate.R;
import com.example.cinerate.admin.AdminHomeActivity;
import com.example.cinerate.admin.adapters.MovieAdapter;
import com.example.cinerate.daos.MovieDAO;
import com.example.cinerate.helper.CinaRateHelper;
import com.example.cinerate.models.Genre;
import com.example.cinerate.models.Language;
import com.example.cinerate.models.Movie;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class MovieFragment extends Fragment {
    public static FragmentManager fragmentManager;
    public static List<Movie> movieList;
    public static MovieAdapter adapter;
    private  List<Movie> filteredList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ExtendedFloatingActionButton addBtn = view.findViewById(R.id.createMovieBtn);
        AutoCompleteTextView genDropDown = view.findViewById(R.id.gen_dropdown);
        AutoCompleteTextView langDropDown = view.findViewById(R.id.lang_dropdown);
        SearchView movieSearchView = view.findViewById(R.id.movieSearchView);
        fragmentManager = getParentFragmentManager();

        movieList = AdminHomeActivity.movieDAO.getAllMovies();
        adapter = new MovieAdapter(movieList, this.getContext());
        RecyclerView recyclerView = view.findViewById(R.id.movieListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);

        movieSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filteredList = AdminHomeActivity.movieDAO.getMoviesByTitle(s);
                adapter.filterMovie(filteredList);
                return false;
            }
        });

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

        genDropDown.setAdapter(genAdapter);
        langDropDown.setAdapter(langAdapter);



        genDropDown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String genName = genDropDown.getText().toString();
                int genId = AdminHomeActivity.genreDAO.getGenIdByName(genName);
                filteredList = AdminHomeActivity.movieDAO.getMoviesByGenre(genId);
                adapter.filterMovie(filteredList);
            }
        });

        genDropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genDropDown.showDropDown();
            }
        });

        langDropDown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String langName = langDropDown.getText().toString();
                int langId = AdminHomeActivity.languageDAO.getLangIdByName(langName);
                filteredList = AdminHomeActivity.movieDAO.getMoviesByLanguage(langId);
                adapter.filterMovie(filteredList);
            }
        });

        langDropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                langDropDown.showDropDown();
            }
        });


        if (addBtn != null) {
            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, new MovieDetailFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        }
    }
}