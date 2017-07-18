package com.gomeos.mvvm.view.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.view.ViewGroup;

import com.gomeos.mvvm.view.factory.ItemViewFactory;
import com.gomeos.mvvm.viewmodel.PagerItemViewModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuyuxuan on 16/6/2.
 */
public class ViewModelPagerAdapter extends BaseViewPagerAdapter {
    private static class ViewHolder {
        PagerItemViewModel itemViewModel;
        ViewDataBinding viewDataBinding;
    }

    private Map<Integer, ViewHolder> buffer;
    private ItemViewFactory itemViewFactory;

    public ViewModelPagerAdapter(Context context) {
        super(context);
        buffer = new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewHolder viewHolder = buffer.get(position);

        if (null == viewHolder) {
            PagerItemViewModel itemViewModel = getViewModelType(position);
            viewHolder = new ViewHolder();
            viewHolder.itemViewModel = itemViewModel;
            viewHolder.viewDataBinding = itemViewFactory.getViewDataBinding(itemViewModel);
        }

        viewHolder.itemViewModel.setItem(getItem(position));
        viewHolder.viewDataBinding.executePendingBindings();

        container.addView(viewHolder.viewDataBinding.getRoot());
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewHolder viewHolder = (ViewHolder) object;
        container.removeView(viewHolder.viewDataBinding.getRoot());
        buffer.remove(object);
        super.destroyItem(container, position, object);
    }


    @Override
    protected final void onDataChange() {
        notifyDataSetChanged();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        ViewHolder viewHolder = (ViewHolder) object;
        return view == viewHolder.viewDataBinding.getRoot();
    }

    @SuppressWarnings("unchecked")
    protected PagerItemViewModel getViewModelType(int position) {
        return (PagerItemViewModel) itemViewFactory.getViewModel(getItem(position));
    }

    public ItemViewFactory getItemViewFactory() {
        return itemViewFactory;
    }

    public void setItemViewFactory(ItemViewFactory itemViewFactory) {
        this.itemViewFactory = itemViewFactory;
    }
}
