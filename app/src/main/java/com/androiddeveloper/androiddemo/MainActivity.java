package com.androiddeveloper.androiddemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView mListViewMainMenu;
    private String[] mStringsMainMenu;
    private ArrayAdapter mArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_main);
        init();

    }

    private void init() {
        mStringsMainMenu = getResources().getStringArray(R.array.main_activity_menu);
        mListViewMainMenu = (ListView)findViewById(R.id.listview_mainactivity_mainmenu);
        mArrayAdapter = new ArrayAdapter
                (this, android.R.layout.simple_list_item_1, mStringsMainMenu);
        mListViewMainMenu.setAdapter(mArrayAdapter);
        mListViewMainMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (mStringsMainMenu[position]) {
                    case "Volley":
                        startActivity(new Intent(MainActivity.this, VolleyActivity.class));
                        break;
                    case "Rxjava":
                        startActivity(new Intent(MainActivity.this, RxJavaActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }


}
