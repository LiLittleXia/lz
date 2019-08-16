package com.lz.library.base.mvp;

import io.reactivex.ObservableTransformer;

/**
 *    desc   : MVP 通用性接口
 */
public interface IMvpView {

    /**
     * 加载中
     */
    void onLoading();

    /**
     * 加载完成
     */
    void onComplete();
//
//    /**
//     * 用于请求的数据为空的状态
//     */
//    void onEmpty();
//
//    /**
//     * 用于请求数据出错
//     */
//    void onError();


    <T> ObservableTransformer<T, T> bindLifecycle();
}