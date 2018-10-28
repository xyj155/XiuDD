package com.example.xiudd.sese.http;


import com.example.xiudd.sese.api.API;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/6/25.
 */

public class RetrofitUtil {
    public static final String BASE_URL = "http://120.79.5.238/";
    private Retrofit retrofit;
    private static RetrofitUtil sInstance;


//    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

    public RetrofitUtil(String url) {
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//// add your other interceptors …
//
//// add logging as last interceptor
//        httpClient.addInterceptor(httpLoggingInterceptor);  // <-- this is the important line!
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .callbackExecutor(executorService)
//                .client(httpClient.build())
                .build();
    }

    public static RetrofitUtil getInstance() {
        synchronized (RetrofitUtil.class) {
            if (sInstance == null) {
                sInstance = new RetrofitUtil(BASE_URL);
            }
        }
        return sInstance;
    }

    public API getServerices() {
        return retrofit.create(API.class);
    }
}