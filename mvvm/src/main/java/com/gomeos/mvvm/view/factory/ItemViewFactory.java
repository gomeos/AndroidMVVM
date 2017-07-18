package com.gomeos.mvvm.view.factory;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;

import com.gomeos.mvvm.utils.ObjectUtils;
import com.gomeos.mvvm.view.DataBindingFactory;
import com.gomeos.mvvm.viewmodel.AbsItemViewModel;

/**
 * Created by chenbaocheng on 16/8/11.
 */
public abstract class ItemViewFactory<ItemType> {
    private Context context;

    public ItemViewFactory() {
    }

    public final AbsItemViewModel<ItemType> getViewModel(ItemType item) {
        return getViewModel(getViewModelType(item));
    }

    @SuppressWarnings("unchecked")
    public final AbsItemViewModel<ItemType> getViewModel(Class<? extends AbsItemViewModel> type) {
        AbsItemViewModel<ItemType> vm = (AbsItemViewModel<ItemType>) ObjectUtils.newInstance(type); //TODO use factory here
        return vm;
    }

    public Class<?> getViewModelClass(ItemType item) {
        return getViewModelType(item);
    }

    protected abstract Class<? extends AbsItemViewModel> getViewModelType(ItemType item);

    protected abstract ViewDataBinding createViewDataBinding(AbsItemViewModel<ItemType> viewModel);

    public final ViewDataBinding getViewDataBinding(AbsItemViewModel<ItemType> viewModel) {
        ViewDataBinding viewDataBinding = createViewDataBinding(viewModel);
        viewModel.setContext(viewDataBinding.getRoot().getContext());
        return viewDataBinding;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return this.context;
    }

    protected final LayoutInflater getInflater() {
        return LayoutInflater.from(context);
    }

    protected final <T extends ViewDataBinding> T inflate(@LayoutRes int layoutId) {
        return DataBindingFactory.inflate(context, layoutId);
    }
}
