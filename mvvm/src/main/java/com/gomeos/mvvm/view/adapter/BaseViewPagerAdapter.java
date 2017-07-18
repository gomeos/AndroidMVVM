package com.gomeos.mvvm.view.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by liuyuxuan on 16/6/2.
 */
public abstract class BaseViewPagerAdapter extends PagerAdapter {
    private final Context context;
    private final List<Object> items;

    public BaseViewPagerAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    public Object getItem(int position) {
        return items.get(position);
    }

    protected abstract void onDataChange();

    public void addItem(Object item) {
        this.items.add(item);
        onDataChange();
    }

    public void addItems(Collection<Object> items) {
        this.items.addAll(items);
        onDataChange();
    }

    public void putItems(Collection<Object> items) {
        this.items.clear();
        this.items.addAll(items);
        onDataChange();
    }

    public void addItems(int position, Collection<Object> items) {
        this.items.addAll(position, items);
        onDataChange();
    }

    public void insertItem(int index, Object Object) {
        this.items.add(index, Object);
        onDataChange();
    }

    public int indexOf(Object Object) {
        return items.indexOf(Object);
    }

    public void clear() {
        this.items.clear();
        onDataChange();
    }

    public Context getContext() {
        return context;
    }
}
