package com.cinema.cintix.startingapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.cinema.cintix.HomePage;
import com.cinema.cintix.R;
import com.cinema.cintix.data.UserData;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;

import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.facebook.login.LoginBehavior.DEVICE_AUTH;

public class LoginActivity extends AppCompatActivity {
    private CallbackManager callbackManager;
    private LoginButton logfb;
    //only for testing need to be deleted and pass the data to server
    UserData user = new UserData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                            Intent i = new Intent(LoginActivity.this, HomePage.class);
                            user.setImage(new URL("https://graph.facebook.com/" + user.getId() + "/picture?type=normal"));
                            i.putExtra("user", user.getName());
                            i.putExtra("id", user.getId());
                            i.putExtra("url",user.getImage().toString());
                            startActivity(i);
                            finish();
                        } catch (JSONException e) {
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }

}