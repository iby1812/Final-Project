package com.cinema.cintix.starting_app;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class Application extends android.app.Application {


    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }
}
