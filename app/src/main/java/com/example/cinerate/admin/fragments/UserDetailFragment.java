package com.example.cinerate.admin.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.cinerate.R;
import com.example.cinerate.admin.AdminHomeActivity;
import com.example.cinerate.admin.adapters.UserAdapter;
import com.example.cinerate.models.User;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

public class UserDetailFragment extends Fragment {
    private boolean isEditMode = false;
    private String  itemRole, itemPassword, itemName, username, password, role;
    private int itemPosition, itemId;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextInputEditText txtEditUsername = view.findViewById(R.id.edtTxtUsername);
        TextInputEditText txtEditPassword = view.findViewById(R.id.edtTxtPassword);
        AutoCompleteTextView dropdownUserRole = view.findViewById(R.id.dropdownUserRole);
        MaterialButton saveUserBtn = view.findViewById(R.id.saveUserBtn);

        String[] roles = {"admin", "user"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), com.google.android.material.R.layout.support_simple_spinner_dropdown_item, roles);
        dropdownUserRole.setAdapter(adapter);

        Bundle bundle = getArguments();

        if (bundle != null) {
            itemName = bundle.getString("itemName");
            itemId = bundle.getInt("itemId");
            itemPassword = bundle.getString("itemPassword");
            itemRole = bundle.getString("itemRole");
            itemPosition = bundle.getInt("itemPosition");
            isEditMode = bundle.getBoolean("isEditMode");
        }

        if (isEditMode) {
            txtEditUsername.setText(itemName);
            txtEditPassword.setText(itemPassword);
            dropdownUserRole.setText(itemRole, false);
            saveUserBtn.setText("Sửa");
        }else {
            saveUserBtn.setText("Thêm");
        }

        dropdownUserRole.setOnItemClickListener((parent, view1, position, id ) ->{
            role = dropdownUserRole.getText().toString();
        });

        saveUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = txtEditUsername.getText().toString().trim();
                password = txtEditPassword.getText().toString().trim();
                role = dropdownUserRole.getText().toString();
                if(!username.isEmpty() && !password.isEmpty()){
                    if(isEditMode){
                        User u = new User(itemId, username, password, role);
                        long rowsAffected = AdminHomeActivity.userDAO.updateUser(u);
                        if(rowsAffected !=0){
                            Toast.makeText(getContext(), "Sửa thành công!", Toast.LENGTH_SHORT).show();
                            UserFragment.userList.set(itemPosition, u);
                            UserFragment.adapter.notifyItemChanged(itemPosition);
                        }else {
                            Toast.makeText(getContext(), "Lỗi!", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        User u = new User(username, password, role);
                        long result = AdminHomeActivity.userDAO.addUser(u);
                        if(result != -1){
                            Toast.makeText(getContext(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
                            UserFragment.userList.add(u);
                            UserFragment.adapter.notifyItemInserted(UserFragment.userList.size() - 1);
                        }else {
                            Toast.makeText(getContext(), "Lỗi!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else {
                    Toast.makeText(getContext(), "Không được bỏ trống!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}