package com.gomeos.mvvm;

import com.gomeos.mvvm.model.UseCaseManager;

/**
 * Created by liuyuxuan on 2016/10/14.
 */

public class FrameworkModule extends Module {

    private static volatile FrameworkModule instance = null;

    public static FrameworkModule getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (FrameworkModule.class) {
            if (instance == null) {
                instance = new FrameworkModule();
            }
        }
        return instance;
    }

    @Override
    protected void onStart(UseCaseManager userCaseManager) {

    }
}
