package com.lz.core;

import com.lz.library.base.mvp.MvpActivity;

import java.util.List;

public class MainActivity extends MvpActivity<MainPresenter> implements MainContract.View{

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void loginError(String msg) {

    }

    @Override
    public void loginSuccess(List<String> data) {

    }
}
