package com.cinema.cintix.AppStarts;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.cinema.cintix.R;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        },2000);
    }
}
