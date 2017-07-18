package com.mx.demo.view.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gomeos.mvvm.view.DataBindingFactory;
import com.gomeos.mvvm.view.ui.BaseFragment;
import com.gomeos.mvvm.viewmodel.proxy.DialogProxy;
import com.mx.demo.DemoModule;
import com.mx.demo.R;
import com.mx.demo.databinding.FragmentMainBinding;
import com.mx.demo.viewmodel.MainViewModel;

public class MainFragment extends BaseFragment {

    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainFragment", "savedInstanceState>>" + savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        //初始化布局
        FragmentMainBinding mainBinding = DataBindingFactory.inflate(getContext(), R.layout.fragment_main);
        //创建viewmodel
        MainViewModel viewModel = DemoModule.get().getViewModelFactory().createViewModel("main_view_model", MainViewModel.class, this);
        getViewModelManager().addViewModel(viewModel);

        //viewmodel里需要使用dialog时，通过dialogProxy在viewmodel里操作dialog
        DialogProxy dialogProxy = new DialogProxy(getFragmentManager(),new MyDialogFragment(),"tag");
        viewModel.setDialogProxy(dialogProxy);

        // 绑定viewmodel
        mainBinding.setModel(viewModel);
        return mainBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("onResume");
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
