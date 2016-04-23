package com.androiddeveloper.androiddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.androiddeveloper.androiddemo.bean.PhoneResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by zzh on 16/4/23.
 */
public class RetrofitActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_retrofit);

        query();
    }



    public interface PhoneService {
        @GET("/apistore/mobilenumber/mobilenumber")
        Call<PhoneResult> getResult(@Header("apikey") String apikey, @Query("phone") String phone);
    }

    private static final String Basic_URL = "http://apis.baidu.com";
    private static final String API_KEY = "d614ea4e26e293c87fb3bd90188b33df";
    public void query(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Basic_URL)
                .build();
        PhoneService phoneService = retrofit.create(PhoneService.class);
        Call<PhoneResult> call = phoneService.getResult(API_KEY,"13888888888");

        call.enqueue(new Callback<PhoneResult>() {
            @Override
            public void onResponse(Call<PhoneResult> call, Response<PhoneResult> response) {
                if (response.isSuccessful()){
                    PhoneResult phoneResult = response.body();
                    if (phoneResult != null){
                        Log.d("zzh",phoneResult.getRetData().getCity());
                    }
                }
            }
            @Override
            public void onFailure(Call<PhoneResult> call, Throwable t) {
                Log.d("zzh","faliure");
            }
        });

    }
}