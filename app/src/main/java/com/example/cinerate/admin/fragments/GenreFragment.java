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

import com.example.cinerate.R;
import com.example.cinerate.admin.AdminHomeActivity;
import com.example.cinerate.admin.adapters.GenreAdapter;
import com.example.cinerate.daos.GenreDAO;
import com.example.cinerate.models.Genre;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class GenreFragment extends Fragment {
    public static GenreAdapter adapter;
    public static FragmentManager fragmentManager;
    public static List<Genre> genreList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_genre, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentManager = getParentFragmentManager();

        ExtendedFloatingActionButton createGenBtn = view.findViewById(R.id.createGenBtn);

        genreList = AdminHomeActivity.genreDAO.getAllGenres();
        adapter = new GenreAdapter(genreList);
        RecyclerView recyclerView = view.findViewById(R.id.genList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);



        if (createGenBtn != null) {
            createGenBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment genreDetailFragment = new GenreDetailFragment();
                    getParentFragmentManager().beginTransaction().
                            replace(R.id.fragment_container, genreDetailFragment).
                            addToBackStack(null).
                            commit();
                }
            });
        }


    }
}