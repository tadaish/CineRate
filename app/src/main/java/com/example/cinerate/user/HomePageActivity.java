package com.example.cinerate.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.example.cinerate.R;
import com.example.cinerate.admin.Data;
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
    Button myListButton;

    List<Movie> homeBannerList;
    List<Movie> entertainmentList;
    List<Movie> popularInterestList;
    public static List<Movie> movieList;
    private String currentUsername;

    public static MovieDAO movieDAO;
    public static GenreDAO genreDAO;
    public static LanguageDAO languageDAO;
    public static UserDAO userDAO;
    public static CommentDAO commentDAO;
    public static RatingDAO ratingDAO;

    MainRecyclerAdapter mainRecyclerAdapter;
    RecyclerView mainRecycler;

    private int getLoggedInUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserAppPrefs", MODE_PRIVATE);
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
        sharedPreferences = getSharedPreferences("UserAppPrefs", MODE_PRIVATE);

        welcomeTextView = findViewById(R.id.welcomeTextView);
        logoutButton = findViewById(R.id.logoutButton);
        loginButton = findViewById(R.id.loginButton);
        myListButton = findViewById(R.id.myListButton);


        updateUI();
        // Xử lý login
        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomePageActivity.this, LoginActivity.class);
            startActivity(intent);
        });


        // Xử lý logout
        logoutButton.setOnClickListener(v -> {

            // Xóa dữ liệu đăng nhập trong SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("LoggedInUserId");
            editor.remove("LoggedInUserName");
            editor.apply();

            // Cập nhật giao diện
            updateUI();
            Toast.makeText(HomePageActivity.this, "Đăng xuất thành công!", Toast.LENGTH_SHORT).show();
        });

        currentUsername = sharedPreferences.getString("LoggedInUserName", null);

        myListButton.setOnClickListener(v -> {
            int userId = getLoggedInUserId();
            String username = sharedPreferences.getString("LoggedInUserName", null);

            if (userId != -1 && username != null) {
                Intent intent = new Intent(HomePageActivity.this, WatchlistActivity.class);
                intent.putExtra("currentUsername", username);
                startActivity(intent);
            } else {
                Toast.makeText(HomePageActivity.this, "Vui lòng đăng nhập để truy cập MyList.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomePageActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //mo CSDL

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
        entertainmentList = new ArrayList<>();
        popularInterestList = new ArrayList<>();

        Data data = new Data(this);
        data.loadData();

        movieList = HomePageActivity.movieDAO.getAllMovies();

        if (movieList != null && !movieList.isEmpty()) {
            homeBannerList.addAll(movieList);
            for (Movie movie : movieList) {
                if (movie.getGenreId() == 3 || movie.getGenreId() == 4 || movie.getGenreId() == 6) {
                    entertainmentList.add(movie);
                }
            }
            for (Movie movie : movieList) {
                if (movie.getGenreId() == 1 || movie.getGenreId() == 2 || movie.getGenreId() == 5 || movie.getGenreId() == 7) {
                    popularInterestList.add(movie);
                }
            }

        } else {
            Toast.makeText(this, "Không có dữ liệu để hiển thị", Toast.LENGTH_SHORT).show();
        }
        setMoviePagerAdapter(homeBannerList);

        List<AllCategory> allCategoryList = new ArrayList<>();
        List<AllCategory> entertainmentList = new ArrayList<>();
        List<AllCategory> popularInterestList = new ArrayList<>();

        String[] categoryNames = {"Kinh dị", "Hành động", "Hài", "Hoạt hình", "Viễn tưởng", "Lãng mạn", "Chiến tranh"};

        for (int i = 1; i <= 7; i++) {
            List<Movie> filteredMovies = new ArrayList<>();
            for (Movie movie : movieList) {
                if (movie.getGenreId() == i) {
                    filteredMovies.add(movie);
                }
            }
            AllCategory category = new AllCategory(i, categoryNames[i - 1], filteredMovies);

            if (i == 3 || i == 4 || i == 6) {
                entertainmentList.add(category);
            }
            if (i == 1 || i == 2 || i == 5 || i == 7) {
                popularInterestList.add(category);
            }
            allCategoryList.add(category);
        }
        setMainRecycler(allCategoryList);

        categoryTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 1:
                        setMoviePagerAdapter(HomePageActivity.this.entertainmentList);
                        setMainRecycler(entertainmentList);
                        return;
                    case 2 :
                        setMoviePagerAdapter(HomePageActivity.this.popularInterestList);
                        setMainRecycler(popularInterestList);
                        return;
                    default:
                        setMoviePagerAdapter(homeBannerList);
                        setMainRecycler(allCategoryList);

                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

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
