package com.androiddeveloper.androiddemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

/**
 * Created by zzh on 16/4/25.
 */
public class TabsActivity extends ToolBarActivity {

    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private TabLayout mTabLayout;

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_layout_tabs;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mTabLayout = (TabLayout)findViewById(R.id.tablelayout_tabsactivity_tab);
        mViewPager = (ViewPager)findViewById(R.id.viepager_tabsactivity);
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
