package com.mx.demo;

import com.gomeos.mvvm.Module;
import com.gomeos.mvvm.model.UseCaseManager;
import com.mx.demo.model.MyUseCase;

/**
 * Created by chenbaocheng on 16/8/14.
 */
public class DemoModule extends Module {
    private static volatile DemoModule instance = null;

    public static DemoModule get() {
        if (instance != null) {
            return instance;
        }
        synchronized (DemoModule.class) {
            if (instance == null) {
                instance = new DemoModule();
            }
        }
        return instance;
    }


    @Override
    protected void onStart(UseCaseManager userCaseManager) {
        userCaseManager.register(MyUseCase.class);
    }
}
