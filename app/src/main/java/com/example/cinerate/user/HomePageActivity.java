package com.example.cinerate.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.example.cinerate.R;
import com.example.cinerate.daos.CommentDAO;
import com.example.cinerate.daos.GenreDAO;
import com.example.cinerate.daos.LanguageDAO;
import com.example.cinerate.daos.MovieDAO;
import com.example.cinerate.daos.RatingDAO;
import com.example.cinerate.daos.UserDAO;
import com.example.cinerate.helper.DatabaseManager;
import com.example.cinerate.models.Movie;
import com.example.cinerate.models.User;
import com.example.cinerate.user.Adapter.MainRecyclerAdapter;
import com.example.cinerate.user.Adapter.MoviePagerAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePageActivity extends AppCompatActivity {

    MoviePagerAdapter moviePagerAdapter;
    TabLayout indicatorTab, categoryTab;
    ViewPager viewPager;
    Button loginButton;
    Button logoutButton;
    SharedPreferences sharedPreferences;
    TextView welcomeTextView;

    List<Movie> homeBannerList;
    List<Movie> tvShowBannerList;
    List<Movie> movieBannerList;
    List<Movie> animeBannerList;
    public static List<Movie> movieList;

    public static MovieDAO movieDAO;
    public static GenreDAO genreDAO;
    public static LanguageDAO languageDAO;
    public static UserDAO userDAO;
    public static CommentDAO commentDAO;
    public static RatingDAO ratingDAO;

    MainRecyclerAdapter mainRecyclerAdapter;
    RecyclerView mainRecycler;
    List<AllCategory> allCategoryList;

    private int getLoggedInUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        return sharedPreferences.getInt("LoggedInUserId", -1); // -1 nếu không tìm thấy ID
    }

    private String getUserName() {
        int userId = getLoggedInUserId();
        if (userId != -1) {
            User user = userDAO.getUserId(userId);
            if (user != null) {
                return user.getUsername();
            }
        }
        return null;
    }

    private void updateUI() {
        int userId = getLoggedInUserId();
        if (userId != -1) {
            // Người dùng đã đăng nhập
            User user = userDAO.getUserId(userId);
            if (user != null) {
                welcomeTextView.setText("Chào " + user.getUsername());
                welcomeTextView.setVisibility(View.VISIBLE);
            } else {
                welcomeTextView.setVisibility(View.GONE);
            }
            loginButton.setVisibility(View.GONE);
            logoutButton.setVisibility(View.VISIBLE);
        } else {
            // Người dùng chưa đăng nhập
            welcomeTextView.setVisibility(View.GONE);
            loginButton.setVisibility(View.VISIBLE);
            logoutButton.setVisibility(View.GONE);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        userDAO = new UserDAO(this);
        sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);

        welcomeTextView = findViewById(R.id.welcomeTextView);
        logoutButton = findViewById(R.id.logoutButton);
        loginButton = findViewById(R.id.loginButton);

        updateUI();

        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomePageActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        logoutButton.setOnClickListener(v -> {
            // Xóa thông tin người dùng từ SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("LoggedInUserId");
            editor.remove("LoggedInUserName");
            editor.apply();

            // Cập nhật giao diện
            updateUI();
            Toast.makeText(HomePageActivity.this, "Đăng xuất thành công!", Toast.LENGTH_SHORT).show();
        });

        //mo CSDL
        DatabaseManager.getInstance(this).open();

        //khoi tao cac lop Data-Acess-Object
        movieDAO = new MovieDAO(this);
        genreDAO = new GenreDAO(this);
        languageDAO = new LanguageDAO(this);
        commentDAO = new CommentDAO(this);
        ratingDAO = new RatingDAO(this);

        updateUI();

        indicatorTab = findViewById(R.id.tab_indicator);
        categoryTab = findViewById(R.id.tabLayout);

        homeBannerList = new ArrayList<>();
        tvShowBannerList = new ArrayList<>();
        movieBannerList = new ArrayList<>();
        animeBannerList = new ArrayList<>();

        movieList = HomePageActivity.movieDAO.getAllMovies();

        if (movieList != null && !movieList.isEmpty()) {
            homeBannerList.addAll(movieList);
            for (Movie movie : movieList) {
                if (movie.getGenreId() == 5) {
                    tvShowBannerList.add(movie);
                }
            }
            for (Movie movie : movieList) {
                if (movie.getGenreId() == 7) {
                    movieBannerList.add(movie);
                }
            }
            for (Movie movie : movieList) {
                if (movie.getGenreId() == 4) {
                    animeBannerList.add(movie);
                }
            }
        } else {
            Toast.makeText(this, "Không có dữ liệu để hiển thị", Toast.LENGTH_SHORT).show();
        }
        setMoviePagerAdapter(homeBannerList);

        categoryTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 1:
                        setMoviePagerAdapter(tvShowBannerList);
                        return;
                    case 2 :
                        setMoviePagerAdapter(movieBannerList);
                        return;
                    case 3 :
                        setMoviePagerAdapter(animeBannerList);
                        return;
                    default:
                        setMoviePagerAdapter(homeBannerList);
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        List<AllCategory> allCategoryList = new ArrayList<>();
        String[] categoryNames = {"Kinh dị", "Hành động", "Hài", "Hoạt hình", "Viễn tưởng", "Lãng mạn", "Chiến tranh"};

        for (int i = 1; i <= 7; i++) {
            List<Movie> filteredMovies = new ArrayList<>();
            for (Movie movie : movieList) {
                if (movie.getGenreId() == i) {
                    filteredMovies.add(movie);
                }
            }
            allCategoryList.add(new AllCategory(i, categoryNames[i - 1], filteredMovies));
        }

        setMainRecycler(allCategoryList);
    }

    private void setMoviePagerAdapter(List<Movie> bannerMoviesList) {
        viewPager = findViewById(R.id.viewPager);
        moviePagerAdapter = new MoviePagerAdapter(this, bannerMoviesList);
        viewPager.setAdapter(moviePagerAdapter);
        indicatorTab.setupWithViewPager(viewPager);

        if (bannerMoviesList != null && !bannerMoviesList.isEmpty()) {
            Timer sliderTimer = new Timer();
            sliderTimer.schedule(new AutoSlider(), 4000, 8000 );

        }
        indicatorTab.setupWithViewPager(viewPager, true);

    }

    class AutoSlider extends TimerTask {

        @Override
        public void run() {
            HomePageActivity.this.runOnUiThread(() -> {
                if (viewPager.getCurrentItem() < homeBannerList.size() - 1) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                } else {
                    viewPager.setCurrentItem(0, true);
                }
            });
        }
    }
    public void setMainRecycler(List<AllCategory> allCategoryList) {

        mainRecycler = findViewById(R.id.main_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mainRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(this, allCategoryList);
        mainRecycler.setAdapter(mainRecyclerAdapter);
    }
}
