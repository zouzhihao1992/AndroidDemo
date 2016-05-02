package com.androiddeveloper.androiddemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.androiddeveloper.androiddemo.R;
import com.androiddeveloper.androiddemo.adapter.ViewPagerAdapterForTab;

/**
 * Created by zzh on 16/4/25.
 */
public class TabsActivity extends ToolBarActivity {

    private ViewPager mViewPager;
    private ViewPagerAdapterForTab mViewPagerAdapterForTab;
    private TabLayout mTabLayout;

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_layout_tabs;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPagerAdapterForTab = new ViewPagerAdapterForTab(getSupportFragmentManager());
        mTabLayout = (TabLayout)findViewById(R.id.tablelayout_tabsactivity_tab);
        mViewPager = (ViewPager)findViewById(R.id.viepager_tabsactivity);
        mViewPager.setAdapter(mViewPagerAdapterForTab);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
