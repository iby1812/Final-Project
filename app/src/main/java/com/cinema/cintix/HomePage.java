package com.cinema.cintix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.cinema.cintix.AppStarts.LoginActivity;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txt= findViewById(R.id.textView);
        txt.setText(LoginActivity.user.getName());

    }
}
