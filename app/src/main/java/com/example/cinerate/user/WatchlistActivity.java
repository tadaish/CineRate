package com.example.cinerate.user;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinerate.R;
import com.example.cinerate.daos.MovieDAO;
import com.example.cinerate.daos.UserDAO;
import com.example.cinerate.models.Movie;
import com.example.cinerate.models.User;
import com.example.cinerate.user.Adapter.WatchlistAdapter;

import java.util.ArrayList;
import java.util.List;

public class WatchlistActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private WatchlistAdapter adapter;
    private UserDAO userDAO;
    private MovieDAO movieDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);

        SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        String currentUsername = sharedPreferences.getString("LoggedInUserName", null);

        if (currentUsername == null) {
            // Xử lý khi không có username
            Toast.makeText(this, "User not found, please login again", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        recyclerView = findViewById(R.id.watchlistRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userDAO = new UserDAO(this);
        movieDAO = new MovieDAO(this);

        User currentUser = userDAO.getUserByUsername(currentUsername);
        if (currentUser != null) {
            List<Integer> watchlistIds = currentUser.getWatchlist();
            List<Movie> watchlistMovies = new ArrayList<>();

            // Duyệt qua danh sách ID và truy xuất thông tin phim
            for (int movieId : watchlistIds) {
                Movie movie = movieDAO.getMovieById(movieId);
                if (movie != null) {
                    watchlistMovies.add(movie);
                }
            }

            adapter = new WatchlistAdapter(this, watchlistMovies);
            recyclerView.setAdapter(adapter);
        } else {
            Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
        }
    }

}
