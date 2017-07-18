package com.gomeos.mvvm.viewmodel;

import com.gomeos.activitystarter.RawActivityStarter;

/**
 * Created by liuyuxuan on 16/8/18.
 */
public interface ViewModelScope extends RawActivityStarter {
    void addViewModel(LifecycleViewModel lifecycleViewModel);

    void removeViewModel(LifecycleViewModel lifecycleViewModel);

    void registerActivityResultReceiver(int requestCode, String viewModelId);

    String getViewModelScopeId();
}
