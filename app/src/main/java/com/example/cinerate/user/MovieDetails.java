package com.example.cinerate.user;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.cinerate.R;
import com.example.cinerate.utils.YouTubeUtils;

public class MovieDetails extends AppCompatActivity  {

    WebView webView;
    TextView movieName;
    Button playButton;

    String mName, mImage,mId, mTrailerUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        webView = findViewById(R.id.movie_trailer);
        movieName = findViewById(R.id.movie_name);
        playButton = findViewById(R.id.play_button);

        mId = getIntent().getStringExtra("movieId");
        mName = getIntent().getStringExtra("movieName");
        mImage= getIntent().getStringExtra("poster_url");
        mTrailerUrl = getIntent().getStringExtra("trailerUrl");
        movieName.setText(mName);
        String videoId = YouTubeUtils.extractVideoId(mTrailerUrl);


        // Configure WebView
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        // Load YouTube IFrame Player API
        String iframeCode = "<html><body style=\"margin:0;padding:0;\">" +
                "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/" + videoId +
                "\" frameborder=\"0\" allowfullscreen></iframe>" +
                "</body></html>";

        webView.loadData(iframeCode, "text/html", "UTF-8");
    }
}
