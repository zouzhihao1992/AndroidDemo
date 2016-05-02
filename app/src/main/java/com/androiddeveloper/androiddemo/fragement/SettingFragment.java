package com.androiddeveloper.androiddemo.fragement;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.androiddeveloper.androiddemo.R;

/**
 * Created by zzh on 16/4/26.
 */
public class SettingFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    private SharedPreferences sharedPreferences;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }


    //监听设置界面的变化。
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        String str = getResources().getString(R.string.open_xxx);
        if (key.equals(str)){
            boolean result = sharedPreferences.getBoolean(key,false);
            Toast.makeText(getActivity(),"open_xxx 设置为" + result,Toast.LENGTH_SHORT).show();
        }
    }
}
