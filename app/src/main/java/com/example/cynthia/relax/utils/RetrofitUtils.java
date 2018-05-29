package com.example.cynthia.relax.utils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public abstract class RetrofitUtils {
    //服务器路径
    private static final String API_SERVER = "http://192.168.16.147:8080";

    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;

    /**
     * 获取Retrofit对象
     *
     * @return
     */
    protected static Retrofit getRetrofit() {

        if (null == mRetrofit) {

            if (null == mOkHttpClient) {
                mOkHttpClient = OkHttp3Utils.getOkHttpClient();
            }
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(API_SERVER + "/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(mOkHttpClient)
                    .build();

        }

        return mRetrofit;
    }
}
