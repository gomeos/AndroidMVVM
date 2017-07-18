package com.mx.demo.view.factory;

import android.databinding.ViewDataBinding;

import com.gomeos.mvvm.view.factory.ItemViewFactory;
import com.gomeos.mvvm.viewmodel.AbsItemViewModel;
import com.mx.demo.R;
import com.mx.demo.databinding.MyGridListitemBinding;
import com.mx.demo.viewmodel.MyItemViewModel;
import com.mx.demo.viewmodel.viewbean.ItemViewBean;
import com.mx.demo.viewmodel.viewbean.MyItemViewBean;

/**
 * Created by zhulianggang on 2017/7/14.
 */

public class MyGridItemViewFactory extends ItemViewFactory<ItemViewBean> {

    public static String getClassName() {
        return MyGridItemViewFactory.class.getName();
    }

    @Override
    protected ViewDataBinding createViewDataBinding(AbsItemViewModel viewModel) {

        if (viewModel instanceof MyItemViewModel) {
            MyGridListitemBinding binding = (MyGridListitemBinding) inflate(R.layout.my_grid_listitem);
            binding.setModel((MyItemViewModel) viewModel);
            return binding;
        }
        //else里支持多种item类型和样式，每种item数据对应一种样式
        return null;
    }

    @Override
    protected Class<? extends AbsItemViewModel> getViewModelType(ItemViewBean item) {
        if (item instanceof MyItemViewBean) {
            return MyItemViewModel.class;
        }
        //else里支持多种item类型和样式，每种item数据对应一种样式
        return null;
    }
}
