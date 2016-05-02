package com.androiddeveloper.androiddemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.androiddeveloper.androiddemo.fragement.TabsFragment;

/**
 * Created by zzh on 16/4/25.
 */
public class ViewPagerAdapterForTab extends FragmentStatePagerAdapter {

    public ViewPagerAdapterForTab(FragmentManager fm) {
        super(fm);
    }

    private String strings[] = new String[]{"tab1","tab2","tab3"};
    @Override
    public Fragment getItem(int position) {
        TabsFragment tabsFragment = new TabsFragment(position);
        return  tabsFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return strings[position];
    }
}
