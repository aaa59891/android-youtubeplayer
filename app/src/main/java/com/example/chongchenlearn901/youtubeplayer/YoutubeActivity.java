package com.example.chongchenlearn901.youtubeplayer;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private static final String TAG = "YoutubeActivity";
    
    public static final String GOOGLE_YOUTUBE_API_KEY = "AIzaSyCV4OhCUdfss4TvpCw9P_Z8d45b9-KX188";

    public static final String YOUTUBE_VEDIO_ID = "ElpitAfkRS4";
    public static final String YOUTUBE_PLAYLIST = "PLXtTjtWmQhg1SsviTmKkWO5n0a_-T0bnD";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        ConstraintLayout constraintLayout = findViewById(R.id.clYoutubePlayer);

        YouTubePlayerView youTubePlayerView = new YouTubePlayerView(this);
        youTubePlayerView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        youTubePlayerView.setBackgroundColor(Color.WHITE);

        constraintLayout.addView(youTubePlayerView);
        youTubePlayerView.initialize(GOOGLE_YOUTUBE_API_KEY, this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {

        if(!wasRestored){
            youTubePlayer.cueVideo(YOUTUBE_VEDIO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        final int REQUEST_CODE = 1;
        if(youTubeInitializationResult.isUserRecoverableError()){
            Log.d(TAG, "onInitializationFailure: recover");
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show();
        }else{
            Log.d(TAG, "onInitializationFailure: not recover");
            String errMsg = String.format("There was an error initializing the youtube player (%s)", youTubeInitializationResult.toString());
            Toast.makeText(this, errMsg, Toast.LENGTH_LONG).show();
        }
    }
}
