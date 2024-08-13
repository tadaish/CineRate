package com.example.cinerate.user;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.example.cinerate.R;
import com.example.cinerate.user.Adapter.MoviePagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {
    MoviePagerAdapter moviePagerAdapter;
    TabLayout tabLayout;
    ViewPager viewPager;
    List<BannerMovies> bannerMoviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        bannerMoviesList = new ArrayList<>();
        bannerMoviesList.add(new BannerMovies(1, "DeadPool", "https://www.imdb.com/title/tt6263850/mediaviewer/rm2199418369/", ""));
        bannerMoviesList.add(new BannerMovies(2, "test", "", ""));
        bannerMoviesList.add(new BannerMovies(3, "test", "", ""));
        bannerMoviesList.add(new BannerMovies(4, "test", "", ""));
        bannerMoviesList.add(new BannerMovies(5, "test", "", ""));

        setMoviePagerAdapter(bannerMoviesList);
        }

    private void setMoviePagerAdapter(List<BannerMovies> bannerMoviesList) {
        viewPager = findViewById(R.id.viewPager);
        moviePagerAdapter = new MoviePagerAdapter(this, bannerMoviesList);
        viewPager.setAdapter(moviePagerAdapter);
    }
}