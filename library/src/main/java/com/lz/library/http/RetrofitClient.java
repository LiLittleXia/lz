package com.lz.library.http;

import com.lz.library.BuildConfig;
import com.lz.library.base.bean.BaseResponseBody;
import com.lz.library.config.HttpConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final long READ_TIMEOUT = 15;

    private static final long WRITE_TIMEOUT = 15;

    private static final long CONNECT_TIMEOUT = 15;

    private final Map<String, Object> serviceMap = new ConcurrentHashMap<>();

    private RetrofitClient() {

    }

    public static RetrofitClient getInstance() {
        return RetrofitHolder.retrofitManagement;
    }

    private static class RetrofitHolder {
        private static final RetrofitClient retrofitManagement = new RetrofitClient();
    }

    private Retrofit createRetrofit(String url) {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(InterceptorUtil.headerInterceptor());
        if (BuildConfig.IS_DEBUG) {
            okHttpBuilder.addInterceptor(InterceptorUtil.logInterceptor());
        }
        OkHttpClient client = okHttpBuilder.build();
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public <T> ObservableTransformer<BaseResponseBody<T>, T>  applySchedulers1() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(result -> {
                    if (result.getRepCode() == HttpCode.SUCCESS_CODE) {
                        return createData(result.getData());
//                        case HttpCode.CODE_TOKEN_INVALID: {
//                            throw new TokenInvalidException();
//                        }
//                        case HttpCode.CODE_ACCOUNT_INVALID: {
//                            throw new AccountInvalidException();
//                        }
                    }
                    throw new ServerResultException(result.getRepCode(), result.getRepMsg());
                });
    }

    public <T> ObservableTransformer<BaseResponseBody<T>, T>  applySchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<BaseResponseBody<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(BaseResponseBody<T> tBaseResponseBody){
                        if (tBaseResponseBody.getRepCode() == HttpCode.SUCCESS_CODE) {
                            return createData(tBaseResponseBody.getData());
                        }else {
                            throw new ServerResultException(tBaseResponseBody.getRepCode(), tBaseResponseBody.getRepMsg());
                        }
                    }
                });
    }

    public <T> Observable<T> createData(T t) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });
    }

    public <T> T getService(Class<T> clz) {
        return getService(clz, HttpConfig.BASE_URL);
    }

    public <T> T getService(Class<T> clz, String host) {
        T value;
        if (serviceMap.containsKey(host)) {
            Object obj = serviceMap.get(host);
            if (obj == null) {
                value = createRetrofit(host).create(clz);
                serviceMap.put(host, value);
            } else {
                value = (T) obj;
            }
        } else {
            value = createRetrofit(host).create(clz);
            serviceMap.put(host, value);
        }
        return value;
    }



}
