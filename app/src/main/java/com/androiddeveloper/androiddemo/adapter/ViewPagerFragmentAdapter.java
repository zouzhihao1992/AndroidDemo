package com.androiddeveloper.androiddemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.androiddeveloper.androiddemo.fragement.ViewPagerFragment;

/**
 * Created by zzh on 16/4/26.
 */
public class ViewPagerFragmentAdapter extends FragmentStatePagerAdapter {

    public static final int  PAGER_COUNT = 3;
    public ViewPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new ViewPagerFragment("Fragment "+position);
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }
}
