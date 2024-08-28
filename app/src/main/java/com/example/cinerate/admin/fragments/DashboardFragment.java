package com.example.cinerate.admin.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cinerate.R;
import com.example.cinerate.admin.AdminHomeActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;


import java.util.ArrayList;
import java.util.Map;


public class DashboardFragment extends Fragment {
    private LineChart lineChart;
    private TextView txtMovieCount, txtGenCount, txtLangCount, txtUserCount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtMovieCount = view.findViewById(R.id.txtMovieCount);
        txtGenCount = view.findViewById(R.id.txtGenCount);
        txtLangCount = view.findViewById(R.id.txtLangCount);
        txtUserCount = view.findViewById(R.id.txtUserCount);

        int movieCount = AdminHomeActivity.movieDAO.getMovieCount();
        int genCount = AdminHomeActivity.genreDAO.getGenreCount();
        int langCount = AdminHomeActivity.languageDAO.getLangCount();
        int userCount = AdminHomeActivity.userDAO.getUserCount();

        txtMovieCount.setText(String.valueOf(movieCount));
        txtGenCount.setText(String.valueOf(genCount));
        txtLangCount.setText(String.valueOf(langCount));
        txtUserCount.setText(String.valueOf(userCount));



        lineChart = view.findViewById(R.id.lineChart);

        // Tạo dữ liệu mẫu cho lượt đánh giá theo tháng
        ArrayList<Entry> reviewEntries = new ArrayList<>();
        reviewEntries.add(new Entry(1, 50));  // Tháng 1
        reviewEntries.add(new Entry(2, 80));  // Tháng 2
        reviewEntries.add(new Entry(3, 60));  // Tháng 3
        reviewEntries.add(new Entry(4, 90));  // Tháng 4
        reviewEntries.add(new Entry(5, 70));  // Tháng 5
        reviewEntries.add(new Entry(6, 100)); // Tháng 6
        reviewEntries.add(new Entry(7, 120)); // Tháng 7
        reviewEntries.add(new Entry(8, 85));  // Tháng 8
        reviewEntries.add(new Entry(9, 95));  // Tháng 9
        reviewEntries.add(new Entry(10, 110)); // Tháng 10
        reviewEntries.add(new Entry(11, 105)); // Tháng 11
        reviewEntries.add(new Entry(12, 130)); // Tháng 12

        LineDataSet lineDataSet = new LineDataSet(reviewEntries, "Lượt đánh giá");
        lineDataSet.setColor(getResources().getColor(R.color.soft_red)); // Màu của đường
        lineDataSet.setValueTextColor(getResources().getColor(R.color.soft_blue)); // Màu của giá trị
        lineDataSet.setValueTextSize(12f); // Kích thước chữ

        LineData lineData = new LineData(lineDataSet);

        lineChart.setData(lineData);

        lineChart.getDescription().setText("Lượt đánh giá theo tháng");
        lineChart.getDescription().setTextSize(14f);
        lineChart.getDescription().setTextColor(getResources().getColor(R.color.soft_blue));
        lineChart.invalidate();
    }
}