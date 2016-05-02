package com.androiddeveloper.androiddemo.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;

import com.androiddeveloper.androiddemo.R;
import com.androiddeveloper.androiddemo.listener.CycleMutiplePermissionListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzh on 16/4/26.
 */
public class MainSettingActivity extends PreferenceActivity {
    private CycleMutiplePermissionListener cycleMutiplePermissionListener;
    private List<String> permissions = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permissions.add(Manifest.permission.WRITE_CONTACTS);
        cycleMutiplePermissionListener = new CycleMutiplePermissionListener(this,permissions);
        cycleMutiplePermissionListener.startRequestPermission();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("zzh","onResume");
        if (cycleMutiplePermissionListener.isQuit){
            finish();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cycleMutiplePermissionListener.onActivityResult(requestCode,resultCode,data);
    }
}


