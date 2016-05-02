package com.androiddeveloper.androiddemo;

import android.app.Application;

import com.karumi.dexter.Dexter;

/**
 * Created by zzh on 16/4/27.
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Dexter.initialize(this);
    }
}