package com.mx.demo;

import android.app.Application;

import com.gomeos.mvvm.ModuleManager;

/**
 * Created by zhulianggang on 2017/7/5.
 */

public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //ModuleManager初始化
        ModuleManager.getInstance().init(this);
        //装载相关的module
        ModuleManager.getInstance().installModule(DemoModule.get());
        //可以有多个module，开始进行装载即可
    }
}
