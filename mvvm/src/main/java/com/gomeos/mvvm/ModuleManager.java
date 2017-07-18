package com.gomeos.mvvm;

import android.content.Context;

import com.gomeos.mvvm.utils.CheckUtils;
import com.gomeos.mvvm.BuildConfig;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuyuxuan on 2017/2/7.
 */

public class ModuleManager {
    private Map<String, Module> modules;
    private static ModuleManager moduleManager;
    private Context context;

    public static ModuleManager getInstance() {
        if (moduleManager != null) {
            return moduleManager;
        }
        synchronized (ModuleManager.class) {
            if (moduleManager == null) {
                moduleManager = new ModuleManager();
            }
        }
        return moduleManager;
    }

    private ModuleManager() {
        modules = new HashMap<>();
    }

    public void init(Context context) {
        this.context = context;
        if (BuildConfig.logger_debug) {
            Logger.init().methodCount(3)
                    .logLevel(LogLevel.FULL)
                    .methodOffset(2);
        } else {
            Logger.init().methodCount(3)
                    .logLevel(LogLevel.NONE)
                    .methodOffset(2);
        }
        installModule(FrameworkModule.getInstance());
    }

    public void installModule(Module module) {
        CheckUtils.checkNotNull(module);
        modules.put(module.getClass().getName(), module);
        module.onInstall(context);
        module.onStart(module.getUserCaseManager());
    }
}
