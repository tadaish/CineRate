package com.example.cinerate.user;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cinerate.R;
import com.example.cinerate.daos.CommentDAO;
import com.example.cinerate.models.Comment;
import com.example.cinerate.user.Adapter.CommentAdapter;
import com.example.cinerate.utils.YouTubeUtils;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieDetails extends AppCompatActivity {
    WebView webView;
    Button watchlist, postCommentButton;
    RecyclerView commentRecyclerView;
    EditText commentInput;
    CommentDAO commentDAO;
    List<Comment> commentsList;
    CommentAdapter commentAdapter;

    String mId, mName, mImage, mTrailerUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        webView = findViewById(R.id.movie_trailer);
        watchlist = findViewById(R.id.watch_list);
        postCommentButton = findViewById(R.id.post_comment_button);
        commentRecyclerView = findViewById(R.id.comment_recycler_view);
        commentInput = findViewById(R.id.comment_input);

        commentDAO = new CommentDAO(this);

        // Load movie details and trailer
        mId = getIntent().getStringExtra("movieId");
        if (mId == null || mId.isEmpty()) {
            Toast.makeText(this, "Movie ID is missing", Toast.LENGTH_SHORT).show();
            finish(); // Hoặc thực hiện các hành động phù hợp khác
            return;
        }
        mName = getIntent().getStringExtra("movieName");
        mImage = getIntent().getStringExtra("poster_url");
        mTrailerUrl = getIntent().getStringExtra("trailerUrl");

        setupTrailerWebView(mTrailerUrl);

        // Load comments for this movie
        loadComments();

        // Post comment functionality
        postCommentButton.setOnClickListener(v -> {
            int userId = getLoggedInUserId(); // Lấy ID của người dùng đăng nhập
            String commentContent = commentInput.getText().toString().trim();

            if (userId != -1 && !TextUtils.isEmpty(commentContent)) {
                Comment newComment = new Comment(0, Integer.parseInt(mId), userId, commentContent, ""); // Bỏ qua created_at
                commentDAO.addComment(newComment);
                commentInput.setText(""); // Clear input field
                loadComments(); // Reload comments
            } else {
                Toast.makeText(this, "Please log in and enter a comment.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadComments() {
        commentsList = commentDAO.getAllCommentsByMovie(Integer.parseInt(mId));
        commentAdapter = new CommentAdapter(commentsList);
        commentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        commentRecyclerView.setAdapter(commentAdapter);
    }

    private int getLoggedInUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        return sharedPreferences.getInt("LoggedInUserId", -1);
    }

    private void setupTrailerWebView(String trailerUrl) {
        String videoId = YouTubeUtils.extractVideoId(trailerUrl);
        String iframeCode = "<html><body style=\"margin:0;padding:0;\"><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"
                + videoId + "\" frameborder=\"0\" allowfullscreen></iframe></body></html>";

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadData(iframeCode, "text/html", "UTF-8");
    }
}


