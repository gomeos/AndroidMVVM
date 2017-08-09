package com.mx.demo.view.factory;

import android.databinding.ViewDataBinding;

import com.gomeos.mvvm.view.factory.ItemViewFactory;
import com.gomeos.mvvm.viewmodel.AbsItemViewModel;
import com.mx.demo.R;
import com.mx.demo.databinding.MyListitemBinding;
import com.mx.demo.databinding.MyRedListitemBinding;
import com.mx.demo.viewmodel.MyItemViewModel;
import com.mx.demo.viewmodel.MyTextItemViewModel;
import com.mx.demo.viewmodel.viewbean.MyItemViewBean;
import com.mx.demo.viewmodel.viewbean.MyTextItemViewBean;

/**
 * Created by zhulianggang on 2017/7/11.
 */

public class MyItemViewFactory extends ItemViewFactory {

    public static String getClassName() {
        return MyItemViewFactory.class.getName();
    }
    @Override
    protected Class<? extends AbsItemViewModel> getViewModelType(Object item) {
        if (item instanceof MyItemViewBean) {
            return MyItemViewModel.class;
        }
        else  if(item instanceof MyTextItemViewBean){
            return MyTextItemViewModel.class;
        }
        //else里支持多种item类型和样式，每种item数据对应一种样式
        return null;
    }
    @Override
    protected ViewDataBinding createViewDataBinding(AbsItemViewModel viewModel) {
        if (viewModel instanceof MyItemViewModel) {
            MyListitemBinding binding = (MyListitemBinding) inflate(R.layout.my_listitem);
            binding.setModel((MyItemViewModel) viewModel);
            return binding;
        }  else if (viewModel instanceof MyTextItemViewModel) {
            MyRedListitemBinding binding = (MyRedListitemBinding) inflate(R.layout.my_red_listitem);
            binding.setModel((MyTextItemViewModel) viewModel);
            return binding;
        }

        //else里支持多种item类型和样式，每种item数据对应一种样式
        return null;
    }
}
