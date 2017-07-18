package com.gomeos.mvvm.viewmodel;

import android.databinding.ViewDataBinding;

/**
 * Created by liuyuxuan on 16/6/25.
 */
public abstract class ListItemViewModel<DataBindingType extends ViewDataBinding, ItemType> extends AbsItemViewModel<ItemType> {
    public ListItemViewModel() {
        super();
    }
}
