package com.gomeos.mvvm.viewmodel.proxy;

import android.support.v4.app.FragmentManager;

import com.gomeos.mvvm.utils.CheckUtils;
import com.gomeos.mvvm.view.ui.BaseDialogFragment;

import java.lang.ref.WeakReference;

/**
 * Created by zhulianggang on 16/9/23.
 */

public class DialogProxy <T extends BaseDialogFragment>{
    private final WeakReference<FragmentManager> fragmentManager;
    private final T dialogFragment ;
    private final String tag;

    public DialogProxy(FragmentManager fragmentManager,  T dialogFragment, String tag) {
        this.fragmentManager = new WeakReference<>(fragmentManager);
        this.dialogFragment = dialogFragment;
        this.tag = tag;
    }

    public void show() {
        CheckUtils.checkNotNull(dialogFragment);
        CheckUtils.checkNotNull(fragmentManager.get());
        dialogFragment.show(fragmentManager.get(), tag);
    }
    public void dismiss() {
        CheckUtils.checkNotNull(dialogFragment);
        CheckUtils.checkNotNull(fragmentManager.get());
        dialogFragment.dismiss();
    }
    protected T getDialogFragment() {
        return dialogFragment;
    }

}
