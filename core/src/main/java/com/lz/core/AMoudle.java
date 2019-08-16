package com.lz.core;

import com.lz.library.base.mvp.MvpModel;
import com.lz.library.config.AppConfig;
import com.lz.library.http.ApiService;
import com.lz.library.http.RequestCallback;
import com.lz.library.http.RetrofitClient;

import io.reactivex.Observable;

public final class AMoudle extends MvpModel<RequestCallback> {


    public Observable<AppConfig> getTest() {
        return RetrofitClient.getInstance().getService(ApiService.class)
                .queryWeather("")
                .compose(RetrofitClient.getInstance().applySchedulers());
    }


}
