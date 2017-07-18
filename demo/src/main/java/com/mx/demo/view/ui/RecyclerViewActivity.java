package com.mx.demo.view.ui;

import android.os.Bundle;

import com.mx.demo.DemoModule;
import com.mx.demo.R;
import com.mx.demo.databinding.RecyclerviewLayBinding;
import com.mx.demo.viewmodel.MyViewModel;
import com.gomeos.mvvm.view.DataBindingFactory;
import com.gomeos.mvvm.view.ui.BaseActivity;

/**
 * Created by zhulianggang on 2017/7/11.
 */

public class RecyclerViewActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RecyclerviewLayBinding binding = DataBindingFactory.setContentView(this, R.layout.recyclerview_lay);
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
