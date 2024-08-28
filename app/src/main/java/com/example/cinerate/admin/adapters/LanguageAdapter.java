package com.example.cinerate.admin.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinerate.R;
import com.example.cinerate.admin.AdminHomeActivity;
import com.example.cinerate.admin.fragments.LanguageDetailFragment;
import com.example.cinerate.admin.fragments.LanguageFragment;
import com.example.cinerate.models.Language;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder> {
    private List<Language> languageList;

    public LanguageAdapter (List<Language> languageList){
        this.languageList = languageList;
    }

    @NonNull
    @Override
    public LanguageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_lang, parent, false);
        return new LanguageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LanguageViewHolder holder, int position) {
        Language lang = languageList.get(position);
        holder.langNameTxt.setText(lang.getName());

        int itemPosition = holder.getAbsoluteAdapterPosition();

        holder.langEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment languageDetailFragment = new LanguageDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("isEditMode", true);
                bundle.putString("itemName", lang.getName());
                bundle.putInt("itemId", lang.getId());
                bundle.putInt("itemPosition", itemPosition);
                languageDetailFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = LanguageFragment.fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.fragment_container, languageDetailFragment).
                        addToBackStack(null).
                        commit();
            }
        });

        holder.langDelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminHomeActivity.languageDAO.deleteLanguage(lang.getId());
                languageList.remove(itemPosition);
                LanguageFragment.langAdapter.notifyItemRemoved(itemPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return languageList.size();
    }

    public static class LanguageViewHolder extends RecyclerView.ViewHolder{
        public TextView langNameTxt;
        public FloatingActionButton langEditBtn, langDelBtn;

        public LanguageViewHolder(@NonNull View itemView) {
            super(itemView);
            langNameTxt = itemView.findViewById(R.id.langNameTxt);
            langEditBtn = itemView.findViewById(R.id.langEditBtn);
            langDelBtn = itemView.findViewById(R.id.langDelBtn);
        }
    }
}
