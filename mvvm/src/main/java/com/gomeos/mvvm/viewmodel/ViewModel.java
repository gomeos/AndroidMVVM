package com.gomeos.mvvm.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.gomeos.mvvm.utils.CheckUtils;
import com.gomeos.mvvm.event.BroadcastEvent;
import com.gomeos.mvvm.event.EventProxy;
import com.gomeos.mvvm.view.ui.BaseActivity;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import static com.gomeos.mvvm.utils.CheckUtils.checkNotNull;

/**
 * Created by liuyuxuan on 16/4/20.
 */
public abstract class ViewModel extends BaseObservable {
    private static Handler handler;
    private Reference<Context> contextRef;

    protected final void runOnUIThread(@NonNull Runnable runnable, long delayTime) {
        CheckUtils.checkNotNull(runnable);
        if (null == handler) {
            handler = new Handler();
        }
        handler.postDelayed(runnable, delayTime);
    }

    protected final void runOnUIThread(@NonNull Runnable runnable) {
        runOnUIThread(runnable, 0);
    }


    public final Context getContext() {
        return contextRef.get();
    }

    public final void setContext(@NonNull Context context) {
        checkNotNull(context);
        this.contextRef = new WeakReference<>(context);
    }

    public <T extends BroadcastEvent> void postEvent(T event) {
        event.setActivityStarter(BaseActivity.getTopActivityStarter());
        EventProxy.getDefault().post(event);
    }

}
