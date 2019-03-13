package com.cinema.cintix;

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
