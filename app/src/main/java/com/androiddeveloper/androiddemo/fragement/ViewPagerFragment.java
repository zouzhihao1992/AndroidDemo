package com.androiddeveloper.androiddemo.fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androiddeveloper.androiddemo.R;

/**
 * Created by zzh on 16/4/26.
 */
public class ViewPagerFragment extends Fragment {

    private String title;
    public ViewPagerFragment (String title){
        this.title = title;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager,container,false);
        ((TextView)view.findViewById(R.id.textview_fragement_content)).setText(title);
        return view;
    }
}
