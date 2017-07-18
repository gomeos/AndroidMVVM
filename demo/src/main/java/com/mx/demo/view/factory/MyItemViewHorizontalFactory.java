package com.mx.demo.view.factory;

import android.databinding.ViewDataBinding;

import com.mx.demo.R;
import com.mx.demo.databinding.MyHorizontalListitemBinding;
import com.mx.demo.viewmodel.MyItemViewModel;
import com.mx.demo.viewmodel.viewbean.MyItemViewBean;
import com.gomeos.mvvm.view.factory.ItemViewFactory;
import com.gomeos.mvvm.viewmodel.AbsItemViewModel;

/**
 * Created by zhulianggang on 2017/7/12.
 */

public class MyItemViewHorizontalFactory extends ItemViewFactory<MyItemViewBean> {

    public static String getClassName() {
        return MyItemViewHorizontalFactory.class.getName();
    }

    @Override
    protected Class<? extends AbsItemViewModel> getViewModelType(MyItemViewBean item) {
        if (item instanceof MyItemViewBean) {
            return MyItemViewModel.class;
        }
        //else里支持多种item类型和样式，每种item数据对应一种样式
        return null;
    }

    @Override
    protected ViewDataBinding createViewDataBinding(AbsItemViewModel viewModel) {

        if (viewModel instanceof MyItemViewModel) {
            MyHorizontalListitemBinding binding = (MyHorizontalListitemBinding) inflate(R.layout.my_horizontal_listitem);
            binding.setModel((MyItemViewModel) viewModel);
            return binding;
        }
        //else里支持多种item类型和样式，每种item数据对应一种样式
        return null;
    }
}
