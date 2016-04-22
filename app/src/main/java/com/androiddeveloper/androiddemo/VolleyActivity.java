package com.androiddeveloper.androiddemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by zzh on 16/4/21.
 * 参考文章：http://blog.csdn.net/guolin_blog/article/details/17482095   Android Volley完全解析(一)，初识Volley的基本用法
           http://blog.csdn.net/guolin_blog/article/details/17482165    Android Volley完全解析(二)，使用Volley加载网络图片
 */
public class VolleyActivity extends AppCompatActivity {

    private ImageView mImageViewDemoPicture;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_volley);
        mImageViewDemoPicture = (ImageView)findViewById(R.id.imageview_volley_activity_demopicture);

        RequestQueue requestQueue = Volley.newRequestQueue(this);


        StringRequest stringRequest = new StringRequest("http://www.baidu.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("zzh", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("zzh", error.toString());
            }
        });
        requestQueue.add(stringRequest);

        /*post的写法
        StringRequest stringRequest = new StringRequest(Method.POST, url,  listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("params1", "value1");
                map.put("params2", "value2");
                return map;
            }
        };
        */

        //接口用不了
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://m.weather.com.cn/data/101010100.html", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("zzh",response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("zzh",error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);

        ImageRequest imageRequest = new ImageRequest("http://img.blog.csdn.net/20150128153831686", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                mImageViewDemoPicture.setImageBitmap(response);

            }
        },0,0, Bitmap.Config.RGB_565, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(imageRequest);


    }
}