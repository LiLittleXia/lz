package com.lz.library.config;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.Utils;
import com.lz.library.BuildConfig;
import com.lz.library.callback.BaseLifecycleCallback;


/**
 * <pre>
 *     desc         所有的配置
 *     revise       生命周期和application一样
 * </pre>
 */
public enum AppConfig {

    //对象
    INSTANCE;


    public void initConfig(Application application) {
        Utils.init(application);
        BaseLifecycleCallback.getInstance().init(application);
        initARouter();
    }


    private void initARouter() {
        if (BuildConfig.IS_DEBUG) {
            //打印日志
            ARouter.openLog();
            //开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }
        //推荐在Application中初始化
        ARouter.init(Utils.getApp());
    }


}
