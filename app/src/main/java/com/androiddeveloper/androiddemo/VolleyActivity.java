package com.androiddeveloper.androiddemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzh on 16/4/21.
 * 参考文章：http://blog.csdn.net/guolin_blog/article/details/17482095   Android Volley完全解析(一)，初识Volley的基本用法
           http://blog.csdn.net/guolin_blog/article/details/17482165    Android Volley完全解析(二)，使用Volley加载网络图片
 */
public class VolleyActivity extends AppCompatActivity {

    private ImageView mImageViewDemoPicture;
    private ImageView mImageViewDemoPicture2;
    private NetworkImageView mNetworkImageViewDemoPicture;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_volley);
        mImageViewDemoPicture = (ImageView)findViewById(R.id.imageview_volley_activity_demopicture);
        mImageViewDemoPicture2 = (ImageView)findViewById(R.id.imageview_volley_activity_demopicture2);
        mNetworkImageViewDemoPicture = (NetworkImageView)findViewById(R.id.network_image_volley_activity_picture);


        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://www.baidu.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("params1","value1");
                map.put("params2","value2");
                return  map;
             }
        };

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

        //接口用不了 JsonObjectRequest
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://m.weather." +
                "com.cn/data/101010100.html", new Response.Listener<JSONObject>() {
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


        //ImageRequest
        ImageRequest imageRequest = new ImageRequest("http://img.blog.csdn.net" +
                "/20150128153831686", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                mImageViewDemoPicture.setImageBitmap(response);

            }
        },0,0, ImageView.ScaleType.CENTER_CROP ,Bitmap.Config.RGB_565,
                new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });



        requestQueue.add(imageRequest);

        //ImageLoader
        ImageLoader imageLoader = new ImageLoader(requestQueue, new BitMapCache());

        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener
                (mImageViewDemoPicture2,R.mipmap.ic_launcher,R.mipmap.ic_launcher);

        imageLoader.get("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg",
                imageListener,400,400);


        //NetworkImageView
        mNetworkImageViewDemoPicture.setDefaultImageResId(R.mipmap.ic_launcher);
        mNetworkImageViewDemoPicture.setErrorImageResId(R.mipmap.ic_launcher);
        mNetworkImageViewDemoPicture.setImageUrl
                ("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg",imageLoader);

    }
    public class BitMapCache implements ImageLoader.ImageCache {

        private LruCache<String,Bitmap> lruCache;

        public BitMapCache(){
            int MaxSize = 10 * 1024 * 1024;
            lruCache = new LruCache<String,Bitmap>(MaxSize){
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getRowBytes() * value.getHeight();
                }
            };

        }

        @Override
        public Bitmap getBitmap(String url) {
            return lruCache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            lruCache.put(url,bitmap);
        }
    }
}