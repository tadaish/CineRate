package com.example.cinerate.admin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.cinerate.R;
import com.example.cinerate.admin.fragments.DashboardFragment;
import com.example.cinerate.admin.fragments.GenreFragment;
import com.example.cinerate.admin.fragments.LanguageFragment;
import com.example.cinerate.admin.fragments.MovieFragment;
import com.example.cinerate.admin.fragments.UserFragment;
import com.example.cinerate.daos.CommentDAO;
import com.example.cinerate.daos.GenreDAO;
import com.example.cinerate.daos.LanguageDAO;
import com.example.cinerate.daos.MovieDAO;
import com.example.cinerate.daos.RatingDAO;
import com.example.cinerate.daos.UserDAO;
import com.example.cinerate.helper.CinaRateHelper;
import com.example.cinerate.helper.DatabaseManager;
import com.example.cinerate.models.Genre;
import com.example.cinerate.models.Language;
import com.example.cinerate.models.Movie;
import com.example.cinerate.models.Rating;
import com.example.cinerate.models.User;
import com.example.cinerate.user.HomePageActivity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class AdminHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private MaterialToolbar toolbar;
    private TextView toolbarTitle;;
    public static MovieDAO movieDAO;
    public static  GenreDAO genreDAO;
    public static  LanguageDAO languageDAO;
    public static  UserDAO userDAO;
    public static  CommentDAO commentDAO;
    public static  RatingDAO ratingDAO;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_home);

        sharedPreferences = getSharedPreferences("AdminAppPrefs", MODE_PRIVATE);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbarTitle = findViewById(R.id.toolbar_title);

        drawerLayout = findViewById(R.id.main);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashboardFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_dashboard);
        }

//        deleteDatabase("cinerate.db");


        //khoi tao cac lop Data-Acess-Object
        movieDAO = new MovieDAO(this);
        genreDAO = new GenreDAO(this);
        languageDAO = new LanguageDAO(this);
        commentDAO = new CommentDAO(this);
        ratingDAO = new RatingDAO(this);
        userDAO = new UserDAO(this);


        Data.loadData();



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_dashboard){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashboardFragment()).commit();
            toolbarTitle.setText("Dashboard");
        }else if (itemId == R.id.nav_movie){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MovieFragment()).commit();
            toolbarTitle.setText("Movie");
        }else if (itemId == R.id.nav_genre) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GenreFragment()).commit();
            toolbarTitle.setText("Genre");
        }else if (itemId == R.id.nav_language) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LanguageFragment()).commit();
            toolbarTitle.setText("Language");;
        }else if (itemId == R.id.nav_user) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UserFragment()).commit();
            toolbarTitle.setText("User");
        }else if (itemId == R.id.nav_logout){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("LoggedInAdminId");
            editor.remove("LoggedInAdminName");
            editor.apply();

            Intent intent = new Intent(AdminHomeActivity.this, HomePageActivity.class);
            startActivity(intent);
            finish();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onBackPressed(){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DatabaseManager.getInstance(this).close();
    }


}