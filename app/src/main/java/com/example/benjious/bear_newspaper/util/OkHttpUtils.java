package com.example.benjious.bear_newspaper.util;


import android.os.Handler;
import android.os.Looper;

import com.squareup.okhttp.OkHttpClient;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

/**
 * Created by Benjious on 2017/1/8.
 */

public class OkHttpUtils {
    public static final String TAG="OkHttpUtils";
    private static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mDelivery;

    public OkHttpUtils() {
        mOkHttpClient=new OkHttpClient();
        mOkHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient.setWriteTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
        //cookie enabled
        mOkHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));

        //这里定义一个handler,方便转回主线程执行操作
        mDelivery=new Handler(Looper.getMainLooper());
    }


}
