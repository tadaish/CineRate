package com.example.cinerate.admin.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cinerate.R;
import com.example.cinerate.daos.GenreDAO;
import com.example.cinerate.models.Genre;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;


public class GenreDetailFragment extends Fragment {
    private boolean isEditMode = false;
    private String itemName, genName;
    private int itemPosition, itemId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_genre, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextInputEditText genEditText = view.findViewById(R.id.genEditText);
        MaterialButton genSaveBtn = view.findViewById(R.id.genSaveBtn);
        Bundle bundle = getArguments();

        if (bundle != null) {
            itemName = bundle.getString("itemName");
            itemId = bundle.getInt("itemId");
            itemPosition = bundle.getInt("itemPosition");
            isEditMode = bundle.getBoolean("isEditMode");
        }

        if (isEditMode) {
            genEditText.setText(itemName);
            genSaveBtn.setText("Sửa");
        }else {
            genSaveBtn.setText("Thêm");
        }

        if (genSaveBtn != null && genEditText != null) {
            genSaveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    genName = genEditText.getText().toString().trim();
                    if (!genName.isEmpty()) {
                        if (isEditMode) {
                            String newGenName = genEditText.getText().toString();
                            Genre gen = new Genre(itemId, newGenName);
                            long rowsAffected = GenreFragment.dao.updateGenre(gen);
                            if (rowsAffected != 0) {
                                Toast.makeText(getContext(), "Sửa thành công!", Toast.LENGTH_SHORT).show();
                                GenreFragment.adapter.notifyItemChanged(itemPosition);
                                GenreFragment.dao.close();
                            } else {
                                Toast.makeText(getContext(), "Lỗi!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Genre gen = new Genre(genName);
                            long result = GenreFragment.dao.addGenre(gen);
                            if (result != -1) {
                                Toast.makeText(getContext(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
                                GenreFragment.adapter.notifyItemInserted(GenreFragment.genreList.size() -1);
                                GenreFragment.dao.close();
                            } else {
                                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }else {
                        Toast.makeText(getContext(), "Không được bỏ trống", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}