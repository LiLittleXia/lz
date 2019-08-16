package com.lz.library.http;

import android.text.TextUtils;

import com.blankj.utilcode.util.AppUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class InterceptorUtil {

    private static final String DEVICE_VERSION = android.os.Build.VERSION.RELEASE;

    private static final String DEVICE_MODEL = android.os.Build.MODEL;

    private static final String DEVICE_MANUFACTURER = android.os.Build.MANUFACTURER;

    private static final String USER_AGENT = "ChiDuiYao/" + AppUtils.getAppVersionName() + "(" + "Android" + ";"  + DEVICE_VERSION + ";" + DEVICE_MANUFACTURER + DEVICE_MODEL + ")";


    public static Interceptor headerInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder builder = originalRequest.newBuilder();
                builder.header("User-Agent", USER_AGENT);
//                        .header("code", header);
                Request request = builder.build();
                return chain.proceed(request);
            }
        };
    }

    public static Interceptor logInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }
}
