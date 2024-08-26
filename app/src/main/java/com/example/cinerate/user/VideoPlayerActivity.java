package com.example.cinerate.user;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DefaultDataSource;
import androidx.media3.datasource.DefaultDataSourceFactory;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.SimpleExoPlayer;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.extractor.Extractor;
import androidx.media3.ui.PlayerView;

import com.example.cinerate.R;

import javax.sql.DataSource;

public class VideoPlayerActivity extends AppCompatActivity {

    private PlayerView videoPlayer;
    private ExoPlayer exoPlayer;
    private static  final String File_url="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        videoPlayer = findViewById(R.id.exo_player);

    }

    private void setUpExoPlayer(String url) {
        exoPlayer = new ExoPlayer.Builder(this).build();
        videoPlayer.setPlayer(exoPlayer);
        DefaultDataSource.Factory dataSourceFactory = new DefaultDataSource.Factory(this);
        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(url));
        exoPlayer.setMediaItem(mediaItem);
        exoPlayer.prepare();
        exoPlayer.setPlayWhenReady(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
