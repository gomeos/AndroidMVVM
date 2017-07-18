package com.gomeos.mvvm.view.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by chenbaocheng on 16/5/5.
 */
public abstract class BaseListAdapter<ItemType> extends BaseAdapter {
    protected final Context context;
    private final List<ItemType> items;


    public BaseListAdapter(Context context) {
        this.items = new ArrayList<>();
        this.context = context;
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public ItemType getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    protected abstract void onDataChange();

    public void addItem(ItemType item) {
        this.items.add(item);
        onDataChange();
    }

    public void addItems(Collection<ItemType> items) {
        this.items.addAll(items);
        onDataChange();
    }

    public void putItems(Collection<ItemType> items) {
        this.items.clear();
        this.items.addAll(items);
        onDataChange();
    }

    public void removeItem(ItemType itemType) {
        items.remove(itemType);
    }

    public void clear() {
        this.items.clear();
        onDataChange();
    }

    public void addItems(int position, Collection<ItemType> items) {
        this.items.addAll(position, items);
        onDataChange();
    }

    public int indexOf(ItemType itemType) {
        return items.indexOf(itemType);
    }

    public void insertItem(int index, ItemType itemType) {
        this.items.add(index, itemType);
        onDataChange();
    }

    protected Context getContext() {
        return this.context;
    }
}
