package com.example.authenticationfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.MediaController;
import android.widget.VideoView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        VideoView videoView;
        videoView = findViewById(R.id.videoview);
        String path = "android.resource://"+getPackageName()+"/"+R.raw.video;
        Uri uri =Uri.parse(path);
        videoView.setVideoURI(uri);
        //MediaController mediaController = new MediaController(this);
        //videoView.setMediaController(mediaController);
        //mediaController.setAnchorView(videoView);
        videoView.start();
        Handler handler = new Handler();
        Intent intent1 = new Intent(SplashScreen.this,MainActivity.class);
        Intent intent2 = new Intent(SplashScreen.this,HomePage.class);
        SharedPreferences sf=getSharedPreferences("myfile", Context.MODE_PRIVATE);
        String p=sf.getString("email","");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(p==""){
                    startActivity(intent1);
                }
                else{
                    startActivity(intent2);
                }
                finish();
            }
        },4000);
    }
}