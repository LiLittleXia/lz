package com.lz.core;

import com.lz.library.base.mvp.IMvpView;

import java.util.List;

public class MainContract {

    public interface View extends IMvpView {

        void loginError(String msg);

        void loginSuccess(List<String> data);
    }

    public interface Presenter {
        void login(String account, String password);
    }
}
