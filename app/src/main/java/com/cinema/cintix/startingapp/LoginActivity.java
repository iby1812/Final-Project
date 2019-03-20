package com.cinema.cintix.startingapp;

import android.content.Intent;
import android.os.Bundle;
import com.cinema.cintix.data.UserData;
import com.cinema.cintix.HomePage;
import com.cinema.cintix.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    //only for testing need to be deleted and pass the data to server
    public static final UserData user = new UserData();

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        initFacebookLoginButton();
    }

    private void initFacebookLoginButton() {

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Profile profile=Profile.getCurrentProfile();
                        if(profile!=null) {
                            user.setName(profile.getName());
                            user.setId(profile.getId());
                        }
                        Intent i = new Intent(LoginActivity.this, HomePage.class);
                        i.putExtra("user",user.getName());
                        startActivity(i);
                        finish();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

}
