package com.androiddeveloper.androiddemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by zzh on 16/4/25.
 */
public abstract class ToolBarActivity extends AppCompatActivity {
    protected abstract int getContentViewLayout();
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewLayout());
        toolbar = (Toolbar)findViewById(R.id.shared_toolbar);
        setSupportActionBar(toolbar);
    }
}
