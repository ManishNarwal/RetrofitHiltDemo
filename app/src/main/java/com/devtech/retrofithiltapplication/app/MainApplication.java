package com.devtech.retrofithiltapplication.app;

import android.app.Application;

import com.devtech.retrofithiltapplication.BuildConfig;

import dagger.hilt.android.HiltAndroidApp;
import timber.log.Timber;
import timber.log.Timber.DebugTree;

@HiltAndroidApp
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) Timber.plant(new DebugTree());
    }
}
