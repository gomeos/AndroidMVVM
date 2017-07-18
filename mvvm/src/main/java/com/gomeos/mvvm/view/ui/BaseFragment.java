package com.gomeos.mvvm.view.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gomeos.mvvm.viewmodel.LifecycleViewModel;
import com.gomeos.mvvm.viewmodel.ViewModelManager;
import com.gomeos.mvvm.viewmodel.ViewModelScope;

/**
 * Created by liuyuxuan on 16/4/20.
 */
//TODO 监测生命状态

public class BaseFragment extends Fragment implements ViewModelScope {
    private FragmentDelegate fragmentDelegate = new FragmentDelegate();


    public final ViewModelManager getViewModelManager() {
        return fragmentDelegate.getViewModelManager();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentDelegate.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentDelegate.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        fragmentDelegate.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        fragmentDelegate.onViewStateRestored(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentDelegate.onCreateView(inflater, container, savedInstanceState);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentDelegate.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentDelegate.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        fragmentDelegate.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        fragmentDelegate.onStop();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        fragmentDelegate.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentDelegate.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        fragmentDelegate.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        fragmentDelegate.onResume();
    }

    public RunState getRunState() {
        return fragmentDelegate.getRunState();
    }

    @Override
    public void addViewModel(LifecycleViewModel lifecycleViewModel) {
        fragmentDelegate.addViewModel(lifecycleViewModel);
    }

    @Override
    public void removeViewModel(LifecycleViewModel lifecycleViewModel) {
        fragmentDelegate.removeViewModel(lifecycleViewModel);
    }

    public void registerActivityResultReceiver(int requestCode, String receiverId) {
        fragmentDelegate.registerActivityResultReceiver(requestCode, receiverId);
    }

    @Override
    public String getViewModelScopeId() {
        return fragmentDelegate.getViewModelScopeId();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fragmentDelegate.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        fragmentDelegate.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        fragmentDelegate.startActivityForResult(intent, requestCode);
    }
}
