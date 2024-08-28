package com.example.cinerate.admin.adapters;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinerate.R;
import com.example.cinerate.admin.AdminHomeActivity;
import com.example.cinerate.admin.fragments.GenreDetailFragment;
import com.example.cinerate.admin.fragments.GenreFragment;
import com.example.cinerate.models.Genre;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder> {
    private List<Genre> genList;

    public GenreAdapter(List<Genre> genList){
        this.genList = genList;
    }


    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_gen, parent, false);
        return new GenreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int position) {
        Genre gen = genList.get(position);
        holder.genNameTxt.setText(gen.getName());

        int item_position = holder.getAbsoluteAdapterPosition();

        holder.genEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment genreDetailFragment = new GenreDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("isEditMode", true);
                bundle.putString("itemName", gen.getName());
                bundle.putInt("itemId", gen.getId());
                bundle.putInt("itemPosition", item_position);
                genreDetailFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = GenreFragment.fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.fragment_container, genreDetailFragment).
                        addToBackStack(null).
                        commit();
            }
        });

        holder.genDelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminHomeActivity.genreDAO.deleteGenre(gen.getId());
                genList.remove(item_position);
                GenreFragment.adapter.notifyItemRemoved(item_position);
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemCount() {
        return genList.size();
    }

    public static class GenreViewHolder extends RecyclerView.ViewHolder{
        public TextView genNameTxt;
        public FloatingActionButton genEditBtn, genDelBtn;

        public GenreViewHolder(View itemView){
            super(itemView);
            genNameTxt = itemView.findViewById(R.id.genNameTxt);
            genEditBtn = itemView.findViewById(R.id.genEditBtn);
            genDelBtn = itemView.findViewById(R.id.genDelBtn);
        }
    }


}
