package com.example.cinerate.user;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.example.cinerate.R;
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
    List<BannerMovies> homeBannerList;
    List<BannerMovies> tvShowBannerList;
    List<BannerMovies> movieBannerList;
    List<BannerMovies> animeBannerList;

    MainRecyclerAdapter mainRecyclerAdapter;
    RecyclerView mainRecycler;
    List<AllCategory> allCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        indicatorTab = findViewById(R.id.tab_indicator);
        categoryTab = findViewById(R.id.tabLayout);

//        homeBannerList = new ArrayList<>();
//        homeBannerList.add(new BannerMovies(1, "DeadPool", "https://www.imdb.com/title/tt6263850/mediaviewer/rm2199418369/", ""));
//        homeBannerList.add(new BannerMovies(2, "test", "", ""));
//        homeBannerList.add(new BannerMovies(3, "test", "", ""));
//        homeBannerList.add(new BannerMovies(4, "test", "", ""));
//        homeBannerList.add(new BannerMovies(5, "test", "", ""));
//
//        tvShowBannerList = new ArrayList<>();
//        tvShowBannerList.add(new BannerMovies(1, "DeadPool", "https://www.imdb.com/title/tt6263850/mediaviewer/rm2199418369/", ""));
//        tvShowBannerList.add(new BannerMovies(2, "test", "", ""));
//        tvShowBannerList.add(new BannerMovies(3, "test", "", ""));
//        tvShowBannerList.add(new BannerMovies(4, "test", "", ""));
//        tvShowBannerList.add(new BannerMovies(5, "test", "", ""));
//
//        movieBannerList = new ArrayList<>();
//        movieBannerList.add(new BannerMovies(1, "DeadPool", "https://www.imdb.com/title/tt6263850/mediaviewer/rm2199418369/", ""));
//        movieBannerList.add(new BannerMovies(2, "test", "", ""));
//        movieBannerList.add(new BannerMovies(3, "test", "", ""));
//        movieBannerList.add(new BannerMovies(4, "test", "", ""));
//        movieBannerList.add(new BannerMovies(5, "test", "", ""));
//
//        animeBannerList = new ArrayList<>();
//        animeBannerList.add(new BannerMovies(1, "DeadPool", "https://www.imdb.com/title/tt6263850/mediaviewer/rm2199418369/", ""));
//        animeBannerList.add(new BannerMovies(2, "test", "", ""));
//        animeBannerList.add(new BannerMovies(3, "test", "", ""));
//        animeBannerList.add(new BannerMovies(4, "test", "", ""));
//        animeBannerList.add(new BannerMovies(5, "test", "", ""));
//
//        setMoviePagerAdapter(homeBannerList);
//
//        categoryTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                switch (tab.getPosition()) {
//                    case 1:
//                        setMoviePagerAdapter(tvShowBannerList);
//                        return;
//                    case 2 :
//                        setMoviePagerAdapter(movieBannerList);
//                        return;
//                    case 3 :
//                        setMoviePagerAdapter(animeBannerList);
//                        return;
//                    default:
//                        setMoviePagerAdapter(homeBannerList);
//                }
//            }
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//            }
//        });
//
//        List<CategoryItem> homeCatListItem1 = new ArrayList<>();
//        homeCatListItem1.add(new CategoryItem(1, "Love and Thunder", "", ""));
//        homeCatListItem1.add(new CategoryItem(1, "Love and Thunder", "", ""));
//        homeCatListItem1.add(new CategoryItem(1, "Love and Thunder", "", ""));
//        homeCatListItem1.add(new CategoryItem(1, "Love and Thunder", "", ""));
//
//        allCategoryList = new ArrayList<>();
//        allCategoryList.add(new AllCategory(1, "Hollywood",homeCatListItem1));
//        allCategoryList.add(new AllCategory(1, "Bollywood",homeCatListItem1));
//        allCategoryList.add(new AllCategory(1, "Anime",homeCatListItem1));
//
//        setMainRecycler(allCategoryList);
//    }
//
//    private void setMoviePagerAdapter(List<BannerMovies> bannerMoviesList) {
//        viewPager = findViewById(R.id.viewPager);
//        moviePagerAdapter = new MoviePagerAdapter(this, bannerMoviesList);
//        viewPager.setAdapter(moviePagerAdapter);
//        indicatorTab.setupWithViewPager(viewPager);
//
//        Timer sliderTimer = new Timer();
//        sliderTimer.schedule(new AutoSlider(), 4000 , 6000);
//        indicatorTab.setupWithViewPager(viewPager, true);
//    }
//
//    class AutoSlider extends TimerTask {
//
//        @Override
//        public void run() {
//            HomePageActivity.this.runOnUiThread(() -> {
//                if (viewPager.getCurrentItem() < homeBannerList.size() - 1) {
//                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
//                } else {
//                    viewPager.setCurrentItem(0);
//                }
//            });
//        }
//    }
//    public void setMainRecycler(List<AllCategory> allCategoryList) {
//
//        mainRecycler = findViewById(R.id.main_recycler);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
//        mainRecycler.setLayoutManager(layoutManager);
//        mainRecyclerAdapter = new MainRecyclerAdapter(this, allCategoryList);
//        mainRecycler.setAdapter(mainRecyclerAdapter);
//    }
}
}
