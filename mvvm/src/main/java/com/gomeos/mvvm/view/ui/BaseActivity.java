package com.gomeos.mvvm.view.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gomeos.activitystarter.ActivityIdentifiable;
import com.gomeos.activitystarter.ActivityResultManager;
import com.gomeos.activitystarter.ActivityStarter;
import com.gomeos.activitystarter.ActivityStarterAware;
import com.gomeos.mvvm.utils.CheckUtils;
import com.gomeos.mvvm.FrameworkModule;
import com.gomeos.mvvm.event.EventProxy;
import com.gomeos.mvvm.view.DataBindingFactory;
import com.gomeos.mvvm.viewmodel.ActivityStartViewModel;
import com.gomeos.mvvm.viewmodel.LifecycleViewModel;
import com.gomeos.mvvm.viewmodel.ViewModelManager;
import com.gomeos.mvvm.viewmodel.ViewModelScope;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

//import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by liuyuxuan on 16/4/20.
 * 1,分配ViewModel的职责;
 * 2,提供viewModel的共享数据;
 * 3,提供ViewModel的通信;
 */
//TODO 监测生命状态
public class BaseActivity extends AppCompatActivity implements ViewModelScope ,ActivityIdentifiable,ActivityStarterAware{
    private final String UUID_KEY = "UUID_KEY_FRAMEWORK2_" + getClass().getName();
    private RunState runState;
    private boolean isHasFocus = false;
    private final List<Reference<BaseFragment>> fragments = new LinkedList<>();
    private ViewModelManager viewModelManager;
    private static WeakReference<LifecycleViewModel> activityStartViewModelRef;
    private LifecycleViewModel activityStartViewModel;
    private String uuid;

    public static ActivityStarter getTopActivityStarter() {
        return activityStartViewModelRef == null ? null : activityStartViewModelRef.get();
    }

    //TODO wrap the real activity starter
    @Override
    public ActivityStarter getActivityStarter() {
        return activityStartViewModel;
    }

    private List<BaseFragment> getFragments() {
        List<BaseFragment> baseFragments = new LinkedList<>();
        ListIterator<Reference<BaseFragment>> listIterator = fragments.listIterator();
        while (listIterator.hasNext()) {
            Reference<BaseFragment> fragmentReference = listIterator.next();
            BaseFragment baseFragment = fragmentReference.get();
            if (null == baseFragment) {
                listIterator.remove();
            } else {
                baseFragments.add(baseFragment);
            }
        }
        return baseFragments;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        viewModelManager.restart();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof BaseFragment) {
            fragments.add(new SoftReference<>((BaseFragment) fragment));
        }
    }

    @Override
    public final void registerActivityResultReceiver(int requestCode, String receiverId) {
        viewModelManager.registerActivityResultReceiver(requestCode, receiverId);
    }

    @Override
    public String getViewModelScopeId() {
        return uuid;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        viewModelManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus != isHasFocus) {
            getViewModelManager().onWindowFocusChanged(hasFocus);
            isHasFocus = hasFocus;
        }
    }

    public RunState getRunState() {
        return this.runState;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            uuid = savedInstanceState.getString(UUID_KEY);
            CheckUtils.checkNotNull(uuid, "uuid is null");
        } else {
            uuid = UUID.randomUUID().toString();
        }
        ActivityResultManager.getInstance().onActivityCreate(this, savedInstanceState);
        this.runState = RunState.Created;
        init(savedInstanceState);
        ActivityStack.getInstance().onActivityCreate(this);

    }

    private void init(Bundle savedInstanceState) {
        viewModelManager = new ViewModelManager();
        viewModelManager.setSavedInstanceState(savedInstanceState);
        viewModelManager.create();
        EventProxy.getDefault().register(this);
        activityStartViewModel = createStartActivityViewModel();
        addViewModel(activityStartViewModel);
        BaseActivity.activityStartViewModelRef = new WeakReference<>(activityStartViewModel);
    }

    @Override
    public String getActivityId() {
        return uuid;
    }

    @NonNull
    protected LifecycleViewModel createStartActivityViewModel() {
        return FrameworkModule.getInstance().getViewModelFactory()
                .createViewModel("activity_start_view_model", ActivityStartViewModel.class, this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getViewModelManager().start();
        this.runState = RunState.Started;
        EventProxy.getDefault().register(this);
        ActivityStack.getInstance().onActivityStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        getViewModelManager().stop();
        EventProxy.getDefault().unregister(this);
        this.runState = RunState.Stopped;
        View view = getWindow().getDecorView().findViewById(android.R.id.content);
        DataBindingFactory.checkViewDataBinding(view);
        ActivityStack.getInstance().onActivityStop(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getViewModelManager().saveInstanceState(outState);
        outState.putString(UUID_KEY,uuid);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        getViewModelManager().restoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.runState = isFinishing() ? RunState.Destroyed : RunState.Hibernating;
        ActivityStack.getInstance().onActivityDestroy(this);
    }

    @Override
    protected void onResume() {
        this.runState = RunState.Resumed;
        super.onResume();
        View view = getWindow().getDecorView().findViewById(android.R.id.content);
        DataBindingFactory.checkViewDataBinding(view);
        getViewModelManager().resume();
        BaseActivity.activityStartViewModelRef = new WeakReference<>(activityStartViewModel);
        ActivityStack.getInstance().onActivityResume(this);
    }

    @Override
    protected void onPause() {
        this.runState = RunState.Paused;
        super.onPause();
        getViewModelManager().pause();
        ActivityStack.getInstance().onActivityPause(this);
    }

    public final ViewModelManager getViewModelManager() {
        CheckUtils.checkNotNull(viewModelManager);
        return viewModelManager;
    }

    @Override
    public void addViewModel(LifecycleViewModel lifecycle) {
        getViewModelManager().addViewModel(lifecycle);
    }

    @Override
    public void removeViewModel(LifecycleViewModel lifecycle) {
        getViewModelManager().removeViewModel(lifecycle);
    }

    @Override
    public Context getContext() {
        return this;
    }

    public ActivityInfo getActivityInfo() {
        return ActivityStack.getInstance().getActivityInfo(this);
    }
}
