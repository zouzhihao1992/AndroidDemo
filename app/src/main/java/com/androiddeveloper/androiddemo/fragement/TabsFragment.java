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
 * Created by zzh on 16/4/25.
 */
public class TabsFragment extends Fragment {

    private int postion;
    public TabsFragment(int postion) {
        this.postion = postion;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_tab,container,false);
        ((TextView)view.findViewById(R.id.TextView_fragmenttab)).setText("tabs"+postion);
        return view;
    }


}
