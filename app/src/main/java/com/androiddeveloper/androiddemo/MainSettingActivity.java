package com.androiddeveloper.androiddemo;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by zzh on 16/4/26.
 */
public class MainSettingActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
