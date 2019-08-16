package com.lz.library.base.mvp;

import com.lz.library.base.BaseActivity;
import com.lz.library.base.BaseDialog;
import com.lz.library.widget.WaitDialog;

import io.reactivex.ObservableTransformer;

/**
 *    desc   : MVP Activity 基类
 */
public abstract class MvpActivity<P extends MvpPresenter> extends BaseActivity implements IMvpView {

    private P mPresenter;
    private BaseDialog waitingDialog;  //加载dialog


    @Override
    public void initActivity() {
        mPresenter = createPresenter();
        mPresenter.attach(this);
        super.initActivity();
        mPresenter.start();
    }

    @Override
    protected void onDestroy() {
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

    @Override
    public void onLoading() {
        if (waitingDialog == null) {
            waitingDialog = new WaitDialog.Builder(this)
                    .setMessage("加载中...")
                    .show();
        }
        if (!waitingDialog.isShowing()) {
            waitingDialog.show();
        }
    }

    @Override
    public void onComplete() {
        if (waitingDialog != null && waitingDialog.isShowing()) {
            waitingDialog.dismiss();
        }
    }

    /**
     * 绑定生命周期
     */
    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }


}