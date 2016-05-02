package com.androiddeveloper.androiddemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.androiddeveloper.androiddemo.R;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

/**
 * Created by zzh on 16/4/26.
 */
public class sliderActivity extends AppCompatActivity {

    SliderLayout sliderLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_slider);
        sliderLayout =(SliderLayout) findViewById(R.id.sliderlayout_slideractivity);
        TextSliderView textSliderView = new TextSliderView(this);
        textSliderView
                .description("Game of Thrones")
                .image("http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

        TextSliderView textSliderView2 = new TextSliderView(this);
        textSliderView2
                .description("Big Bang theory")
                .image("http://image5.tuku.cn/wallpaper/Landscape%20Wallpapers/8294_2560x1600.jpg");


        sliderLayout.addSlider(textSliderView);
        sliderLayout.addSlider(textSliderView2);
        sliderLayout.startAutoCycle();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sliderLayout.stopAutoCycle();
    }
}
