package com.mx.demo.view.ui;

import android.os.Bundle;

import com.gomeos.mvvm.view.DataBindingFactory;
import com.gomeos.mvvm.view.ui.BaseActivity;
import com.mx.demo.DemoModule;
import com.mx.demo.R;
import com.mx.demo.databinding.SwiperefreshLayBinding;
import com.mx.demo.viewmodel.MyViewModel;

/**
 * Created by zhulianggang on 2017/7/14.
 */

public class SwipeRefreshActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SwiperefreshLayBinding binding = DataBindingFactory.setContentView(this, R.layout.swiperefresh_lay);
        //创建viewmodel
        MyViewModel viewModel = DemoModule.get().getViewModelFactory().createViewModel("myRecycler_view_model", MyViewModel.class, this);
        getViewModelManager().addViewModel(viewModel);
        binding.setModel(viewModel);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
