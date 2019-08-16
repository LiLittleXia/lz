package com.lz.core;

import com.lz.library.base.mvp.MvpPresenter;
import com.lz.library.config.AppConfig;
import com.lz.library.http.BaseSubscriber;
import com.lz.library.http.RequestCallback;

public class MainPresenter extends MvpPresenter<MainContract.View> implements MainContract.Presenter{

    private AMoudle aMoudle;

    @Override
    public void start() {
        aMoudle = new AMoudle();
    }

    @Override
    public void login(String account, String password) {
        aMoudle.getTest()
                .compose(loadingTransformer())
                .compose(getView().bindLifecycle())
                .subscribe(new BaseSubscriber<AppConfig>(new RequestCallback<AppConfig>() {
                    @Override
                    public void onSuccess(AppConfig data) {

                    }
                }));
    }
}
