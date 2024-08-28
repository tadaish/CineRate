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
import com.example.cinerate.admin.AdminHomeActivity;
import com.example.cinerate.models.Language;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;


public class LanguageDetailFragment extends Fragment {
    private boolean isEditMode = false;
    private String itemName, langName;
    private int itemPosition, itemId;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_language, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextInputEditText langEditText = view.findViewById(R.id.langNameEditTxt);
        MaterialButton langSaveBtn = view.findViewById(R.id.saveLangBtn);
        Bundle bundle = getArguments();

        if (bundle != null) {
            itemName = bundle.getString("itemName");
            itemId = bundle.getInt("itemId");
            itemPosition = bundle.getInt("itemPosition");
            isEditMode = bundle.getBoolean("isEditMode");
        }

        if (isEditMode) {
            langEditText.setText(itemName);
            langSaveBtn.setText("Sửa");
        }else {
            langSaveBtn.setText("Thêm");
        }

        if (langSaveBtn != null && langEditText !=null){
            langSaveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    langName = langEditText.getText().toString().trim();
                    if(!langName.isEmpty()){
                        if(isEditMode) {
                            String newLangName = langEditText.getText().toString().trim();
                            Language lang =  new Language(itemId, newLangName);
                            long rowsAffected = AdminHomeActivity.languageDAO.updateLanguage(lang);
                            if (rowsAffected != 0) {
                                Toast.makeText(getContext(), "Sửa thành công!", Toast.LENGTH_SHORT).show();
                                LanguageFragment.langAdapter.notifyItemChanged(itemPosition);
                            } else {
                                Toast.makeText(getContext(), "Lỗi!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Language lang = new Language(langName);
                            long result = AdminHomeActivity.languageDAO.addLanguage(lang);
                            if (result != -1) {
                                Toast.makeText(getContext(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
                                GenreFragment.adapter.notifyItemInserted(GenreFragment.genreList.size() -1);
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