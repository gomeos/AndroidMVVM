package com.gomeos.mvvm.view.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.gomeos.mvvm.view.factory.ItemViewFactory;
import com.gomeos.mvvm.viewmodel.RecyclerItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuyuxuan on 16/5/17.
 */
public class ViewModelRecyclerViewAdapter extends BaseRecyclerAdapter {
    private final List<Class<?>> viewModelTypes;
    private ItemViewFactory itemViewFactory;
    private boolean isLooped;
    private final int LOOP_COUNT = 10000000;

    public ViewModelRecyclerViewAdapter(Context context) {
        super(context);
        this.viewModelTypes = new ArrayList<>();
        setHasStableIds(false);
    }

    public void setLooped(boolean looped) {
        isLooped = looped;
    }

    protected final void onDataChange() {
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        if (isLooped) {
            position = getCount() == 0 ? 0 : position % getCount();
        }
        return position;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final int getItemViewType(int position) {
        Class<?> type = itemViewFactory.getViewModelClass(getItem(position));
        int index = viewModelTypes.indexOf(type);
        if (index == -1) {
            viewModelTypes.add(type);
            index = viewModelTypes.indexOf(type);
        }

        return index;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerItemViewModel recyclerItemViewModel;
        ViewDataBinding viewDataBinding;

        public ViewHolder(RecyclerItemViewModel recyclerItemViewModel, ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            this.recyclerItemViewModel = recyclerItemViewModel;
            this.viewDataBinding = viewDataBinding;
            this.recyclerItemViewModel.setView(viewDataBinding.getRoot());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerItemViewModel vm = (RecyclerItemViewModel) itemViewFactory.getViewModel(viewModelTypes.get(viewType));
        return new ViewHolder(vm, itemViewFactory.getViewDataBinding(vm));
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        RecyclerItemViewModel recyclerItemViewModel = viewHolder.recyclerItemViewModel;
        recyclerItemViewModel.setItem(getItem(position));
        viewHolder.viewDataBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        if (isLooped) {
            if (getCount() > 0) {
                return LOOP_COUNT;
            }
        }
        return getCount();
    }


    @Override
    public Object getItem(int position) {
        if (isLooped) {
            position = getCount() == 0 ? 0 : position % getCount();
        }
        return super.getItem(position);
    }

    public ItemViewFactory getItemViewFactory() {
        return itemViewFactory;
    }

    public void setItemViewFactory(ItemViewFactory itemViewFactory) {
        this.itemViewFactory = itemViewFactory;
    }
}
