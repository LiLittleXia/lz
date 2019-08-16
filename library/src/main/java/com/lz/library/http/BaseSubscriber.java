package com.lz.library.http;

import android.net.ParseException;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.JsonParseException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

public class BaseSubscriber<T> extends DisposableObserver<T> {

    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    private RequestCallback<T> requestCallback;

    public BaseSubscriber(RequestCallback<T> requestCallback) {
        this.requestCallback = requestCallback;
    }

    @Override
    public void onNext(T data) {
        if (requestCallback != null) {
            requestCallback.onSuccess(data);
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        String errmsg;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    errmsg = "网络错误";
                    break;
            }
        } else if (e instanceof BaseException) {
            errmsg = "网络错误";
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            errmsg = "解析错误";
        } else if (e instanceof ConnectException) {
            errmsg = "网络连接失败,请稍后重试";
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            e.printStackTrace();
            errmsg = "证书验证失败";
        } else if (e instanceof ConnectTimeoutException) {
            errmsg = "网络连接超时";
        } else if (e instanceof java.net.SocketTimeoutException) {
            errmsg = "连接超时";
        } else {
            errmsg = "网络连接异常,请稍后重试";
        }
        ToastUtils.showShort(errmsg);
    }

    @Override
    public void onComplete() {

    }
}
