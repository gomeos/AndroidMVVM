package com.gomeos.mvvm.viewmodel;

import com.gomeos.mvvm.view.ui.BaseActivity;
import com.gomeos.mvvm.view.ui.BaseDialogFragment;
import com.gomeos.mvvm.view.ui.BaseFragment;

/**
 * Created by liuyuxuan on 16/6/6.
 */
public interface ViewModelFactory {
    <T extends LifecycleViewModel> T createViewModel(String viewModelTag, Class<T> viewModelClassType, BaseDialogFragment baseDialogFragment);

    <T extends LifecycleViewModel> T createViewModel(String viewModelTag, Class<T> viewModelClassType, BaseFragment baseFragment);

    <T extends LifecycleViewModel> T createViewModel(String viewModelTag, Class<T> viewModelClassType, BaseActivity baseActivity);
}

