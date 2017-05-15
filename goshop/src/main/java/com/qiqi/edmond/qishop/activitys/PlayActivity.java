package com.qiqi.edmond.qishop.activitys;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.qiqi.edmond.qishop.R;

import java.net.URL;

import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;


public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Vitamio.isInitialized(getApplicationContext());

        setContentView(R.layout.activity_play);

        VideoView videoView = (VideoView) findViewById(R.id.surface_view);
        Uri uri=Uri.parse("http://baobab.wdjcdn.com/145076769089714.mp4");
        videoView.setVideoURI(uri);

    }
}
