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
import com.example.cinerate.admin.fragments.UserDetailFragment;
import com.example.cinerate.admin.fragments.UserFragment;
import com.example.cinerate.models.Movie;
import com.example.cinerate.models.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> userList;
    private List<User> orgUserList;

    public UserAdapter (List<User> userList){
        this.userList = userList;
        this.orgUserList = userList;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user, parent, false);
        return new UserAdapter.UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User u = userList.get(position);
        holder.txtUserName.setText(u.getUsername());
        holder.txtUserRole.setText(u.getRole());

        int itemPosition = holder.getAbsoluteAdapterPosition();

        holder.editUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment userDetailFragment = new UserDetailFragment();
                FragmentTransaction fragmentTransaction = UserFragment.fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("itemName", u.getUsername());
                bundle.putString("itemRole", u.getRole());
                bundle.putInt("itemId", u.getId());
                bundle.putInt("itemPosition", itemPosition);
                bundle.putString("itemPassword", u.getPassword());
                bundle.putBoolean("isEditMode", true);
                userDetailFragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.fragment_container, userDetailFragment).
                        addToBackStack(null).
                        commit();
            }
        });

        holder.delUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminHomeActivity.userDAO.deleteUser(u.getId());
                userList = AdminHomeActivity.userDAO.getAllUsers();
                UserFragment.adapter.notifyItemRemoved(itemPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{
        public TextView txtUserName, txtUserRole;
        public FloatingActionButton editUserBtn, delUserBtn;

        public UserViewHolder(@NonNull View itemView){
            super(itemView);
            txtUserName = itemView.findViewById(R.id.txtUserName);
            txtUserRole = itemView.findViewById(R.id.txtUserRole);
            editUserBtn = itemView.findViewById(R.id.editUserBtn);
            delUserBtn = itemView.findViewById(R.id.delUserBtn);
        }
    }

    public void filterUser(List<User> filteredList) {
        if (filteredList == null || filteredList.isEmpty()) {
            userList.clear();
            userList.addAll(orgUserList);
        } else {
            userList.clear();
            userList.addAll(filteredList);
        }
        notifyDataSetChanged();
    }
}
