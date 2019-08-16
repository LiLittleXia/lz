package com.lz.library.base.mvp;

import com.lz.library.base.BaseLazyFragment;

/**
 *    desc   : MVP 懒加载 Fragment 基类
 */
public abstract class MvpLazyFragment<P extends MvpPresenter> extends BaseLazyFragment implements IMvpView {

    private P mPresenter;

    @Override
    protected void initFragment() {
        mPresenter = createPresenter();
        mPresenter.attach(this);
        super.initFragment();
        mPresenter.start();
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detach();
            mPresenter = null;
        }
        super.onDestroy();
    }

    public P getPresenter() {
        return mPresenter;
    }

    protected abstract P createPresenter();
}