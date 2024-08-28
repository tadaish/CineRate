package com.example.cinerate.admin;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_home);

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
//
//        movieDAO.addMovie(new Movie("Avengers: Endgame",
//                "Sau những sự kiện tàn khốc trong Avengers: Infinity War, vũ trụ bị hủy hoại do những nỗ lực của Thanos. Với sự giúp đỡ của các đồng minh còn lại, Avengers phải tập hợp lại một lần nữa để đảo ngược hành động của Thanos và khôi phục trật tự cho vũ trụ vĩnh viễn, bất kể hậu quả có thể xảy ra.",
//                2019, "Joe Russo, Anthony Russo",
//                "https://image.tmdb.org/t/p/w342/or06FN3Dka5tukK1e9sl16pB3iy.jpg", "Robert Downey Jr", "https://www.youtube.com/watch?v=hA6hldpSTF8",
//                2, 5));
//
//        movieDAO.addMovie(new Movie("Doraemon: Nobita và bản giao hưởng Địa Cầu ",
//                "Chuẩn bị cho buổi hòa nhạc ở trường, Nobita đang tập thổi sáo - nhạc cụ mà cậu dở tệ. Thích thú trước nốt \"No\" lạc quẻ của Nobita, Micca - cô bé bí ẩn đã mời Doraemon, Nobita cùng nhóm bạn đến \"Farre\" - Cung điện âm nhạc tọa lạc trên một hành tinh nơi âm nhạc sẽ hóa thành năng lượng. Nhằm cứu cung điện này, Micca đang tìm kiếm \"virtuoso\" - bậc thầy âm nhạc sẽ cùng mình biểu diễn! Với bảo bối thần kì \"chứng chỉ chuyên viên âm nhạc\", Doraemon và các bạn đã chọn nhạc cụ, cùng Micca hòa tấu, từng bước khôi phục cung điện. Tuy nhiên, một vật thể sống đáng sợ sẽ xóa số âm nhạc khỏi thế giới đang đến gần, Trái Đất đang rơi vào nguy hiểm... ! Liệu những người bạn nhỏ có thể cứu được \"tương lai âm nhạc\" và cả địa cầu này?",
//                2024, "Kazuaki Imai",
//                "https://image.tmdb.org/t/p/w342/llyJNci45ABJkiGMw819U0tpRzT.jpg", "Wasabi Mizuta, Megumi Oohara", "https://youtu.be/fAfFd2jRT0U?si=ybPr7rT7gYkIM9li",
//                3, 4));
//
//        movieDAO.addMovie(new Movie("The Pianist",
//                "Wladyslaw Szpilman là một nhạc công dương cầm tài năng người Do Thái Ba Lan nhưng cuộc đời bị biến đổi bởi chính sách bắt bớ của quân Phát xít Đức trong Thế chiến thứ hai.\n" +
//                        "\n" +
//                        "Gia đình anh bị bắt đi trại tập trung trong khi anh may mắn trốn thoát nhưng phải lẩn trốn không ngừng trước sự truy lùng ráo riết của quân lính. Tuy sống một cuộc sống tù túng, chui lủi, trong anh vẫn đầy nhiệt huyết, niềm đam mê cháy bỏng với âm nhạc.",
//                2002, "Roman Polanski",
//                "https://image.tmdb.org/t/p/w342/2hFvxCCWrTmCYwfy7yum0GKRi3Y.jpg", "Adrien Brody, Thomas Kretschmann", "https://youtu.be/u_jE7-6Uv7E?si=TzvPo-21wgZvzLXP",
//                2, 7));
//
//        languageDAO.addLanguage(new Language("Tiếng Việt"));
//        languageDAO.addLanguage(new Language("Tiếng Anh"));
//        languageDAO.addLanguage(new Language("Tiếng Nhật"));
//
//        genreDAO.addGenre(new Genre("Kinh dị"));
//        genreDAO.addGenre(new Genre("Hành động"));
//        genreDAO.addGenre(new Genre("Hài"));
//        genreDAO.addGenre(new Genre("Hoạt hình"));
//        genreDAO.addGenre(new Genre("Viễn tưởng"));
//        genreDAO.addGenre(new Genre("Lãng mạn"));
//        genreDAO.addGenre(new Genre("Chiến tranh"));
//
//        userDAO.addUser(new User("Admin","1234", "admin"));
//        userDAO.addUser(new User("u1","1234", "user"));


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
            Toast.makeText(this,  "Logout", Toast.LENGTH_SHORT).show();
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