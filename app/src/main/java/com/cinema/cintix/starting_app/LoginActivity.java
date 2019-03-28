package com.cinema.cintix.starting_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.cinema.cintix.home_screen.HomePage;
import com.cinema.cintix.R;
import com.cinema.cintix.data.UserData;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private CallbackManager callbackManager;
    private LoginButton logfb;
    //only for testing need to be deleted and pass the data to server
    UserData user = new UserData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        initFacebookLoginButton();
    }

    private void initFacebookLoginButton() {
        callbackManager = CallbackManager.Factory.create();
        if (AccessToken.getCurrentAccessToken() != null) {
            LoginManager.getInstance().logInWithReadPermissions(this, Collections.singleton("public_profile"));
            NextActivity();
        } else {
            logfb = findViewById(R.id.log_fb);
            logfb.setVisibility(View.VISIBLE);
            logfb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    NextActivity();
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
            logfb.setLoginBehavior(LoginBehavior.WEB_ONLY);
            logfb.setReadPermissions("public_profile");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void NextActivity() {
        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object, GraphResponse response) {
                        // Application code
                        try {
                            user.setName(object.getString("name"));
                            user.setId(object.getString("id"));
                            user.setImage("https://graph.facebook.com/" + user.getId() + "/picture?type=normal");
                            Intent i = new Intent(LoginActivity.this, HomePage.class);
                            i.putExtra("user", user);
                            startActivity(i);
                            finish();
                        } catch (JSONException e) {
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }

}