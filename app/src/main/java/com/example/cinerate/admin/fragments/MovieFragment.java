package com.example.cinerate.admin.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cinerate.R;
import com.example.cinerate.admin.adapters.MovieAdapter;
import com.example.cinerate.daos.MovieDAO;
import com.example.cinerate.models.Movie;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.List;


public class MovieFragment extends Fragment {
    public static MovieDAO dao;
    public static FragmentManager fragmentManager;
    public static List<Movie> movieList;
    public static MovieAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ExtendedFloatingActionButton addBtn = view.findViewById(R.id.createMovieBtn);
        fragmentManager = getParentFragmentManager();

        dao = new MovieDAO(this.getContext());
        dao.open();

        dao.addMovie(new Movie("Avengers: Endgame",
                "Sau những sự kiện tàn khốc trong Avengers: Infinity War, vũ trụ bị hủy hoại do những nỗ lực của Thanos. Với sự giúp đỡ của các đồng minh còn lại, Avengers phải tập hợp lại một lần nữa để đảo ngược hành động của Thanos và khôi phục trật tự cho vũ trụ vĩnh viễn, bất kể hậu quả có thể xảy ra.",
                2019, "Joe Russo, Anthony Russo",
                "https://image.tmdb.org/t/p/w342/or06FN3Dka5tukK1e9sl16pB3iy.jpg", "Robert Downey Jr", "https://www.youtube.com/watch?v=hA6hldpSTF8",
                2));

        dao.addMovie(new Movie("Doraemon: Nobita và bản giao hưởng Địa Cầu ",
                "Chuẩn bị cho buổi hòa nhạc ở trường, Nobita đang tập thổi sáo - nhạc cụ mà cậu dở tệ. Thích thú trước nốt \"No\" lạc quẻ của Nobita, Micca - cô bé bí ẩn đã mời Doraemon, Nobita cùng nhóm bạn đến \"Farre\" - Cung điện âm nhạc tọa lạc trên một hành tinh nơi âm nhạc sẽ hóa thành năng lượng. Nhằm cứu cung điện này, Micca đang tìm kiếm \"virtuoso\" - bậc thầy âm nhạc sẽ cùng mình biểu diễn! Với bảo bối thần kì \"chứng chỉ chuyên viên âm nhạc\", Doraemon và các bạn đã chọn nhạc cụ, cùng Micca hòa tấu, từng bước khôi phục cung điện. Tuy nhiên, một vật thể sống đáng sợ sẽ xóa số âm nhạc khỏi thế giới đang đến gần, Trái Đất đang rơi vào nguy hiểm... ! Liệu những người bạn nhỏ có thể cứu được \"tương lai âm nhạc\" và cả địa cầu này?",
                2024, "Kazuaki Imai",
                "https://image.tmdb.org/t/p/w342/llyJNci45ABJkiGMw819U0tpRzT.jpg", "Wasabi Mizuta, Megumi Oohara", "https://youtu.be/fAfFd2jRT0U?si=ybPr7rT7gYkIM9li",
                3));

        dao.addMovie(new Movie("The Pianist",
                "Wladyslaw Szpilman là một nhạc công dương cầm tài năng người Do Thái Ba Lan nhưng cuộc đời bị biến đổi bởi chính sách bắt bớ của quân Phát xít Đức trong Thế chiến thứ hai.\n" +
                        "\n" +
                        "Gia đình anh bị bắt đi trại tập trung trong khi anh may mắn trốn thoát nhưng phải lẩn trốn không ngừng trước sự truy lùng ráo riết của quân lính. Tuy sống một cuộc sống tù túng, chui lủi, trong anh vẫn đầy nhiệt huyết, niềm đam mê cháy bỏng với âm nhạc.",
                2002, "Roman Polanski",
                "https://image.tmdb.org/t/p/w342/2hFvxCCWrTmCYwfy7yum0GKRi3Y.jpg", "Adrien Brody, Thomas Kretschmann", "https://youtu.be/u_jE7-6Uv7E?si=TzvPo-21wgZvzLXP",
                2));

        if (addBtn != null) {
            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, new MovieDetailFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        }
    }
}