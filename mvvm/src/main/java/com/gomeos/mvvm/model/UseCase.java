package com.gomeos.mvvm.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static com.gomeos.mvvm.utils.CheckUtils.checkNotNull;

/***
 * 1. 内存中缓存数据;
 * 2. SharedPreferences中持久化数据;
 * 3. 对releam数据库的操作;
 * 4. 网络访问;
 * 5. 接收,发送广播;
 */
public abstract class UseCase {
    private Set<String> holderIds = new HashSet<>();
    private Context context;
    private boolean isOpen = false;

    final void setContext(Context context) {
        this.context = context;
    }

    protected Context getContext() {
        return this.context;
    }

    protected final <Value extends Serializable> void preferencePutInt(@Nullable String fileName, @Nullable String key, int value) {
        checkNotNull(fileName);
        checkNotNull(key);
        checkNotNull(value);
        checkNotNull(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(key, value).apply();
    }

    protected final <Value extends Serializable> void preferencePutLong(@Nullable String fileName, @Nullable String key, @Nullable long value) {
        checkNotNull(fileName);
        checkNotNull(key);
        checkNotNull(value);
        checkNotNull(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putLong(key, value).apply();
    }

    protected final <Value extends Serializable> void preferencePutFloat(@Nullable String fileName, @Nullable String key, float value) {
        checkNotNull(fileName);
        checkNotNull(key);
        checkNotNull(value);
        checkNotNull(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putFloat(key, value).apply();
    }


    protected final <Value extends Serializable> void preferencePutString(@Nullable String fileName, @Nullable String key, String value) {
        checkNotNull(fileName);
        checkNotNull(key);
        checkNotNull(value);
        checkNotNull(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key, value).apply();
    }

    protected float preferenceGetFloat(@Nullable String fileName, @Nullable String key) {
        checkNotNull(fileName);
        checkNotNull(key);
        checkNotNull(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(key, 0);
    }


    protected String preferenceGetString(@Nullable String fileName, @Nullable String key) {
        checkNotNull(fileName);
        checkNotNull(key);
        checkNotNull(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, null);
    }

    protected long preferenceGetLong(@Nullable String fileName, @Nullable String key) {
        checkNotNull(fileName);
        checkNotNull(key);
        checkNotNull(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, 0);
    }

    protected int preferenceGetInt(@Nullable String fileName, @Nullable String key) {
        checkNotNull(fileName);
        checkNotNull(key);
        checkNotNull(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, 0);
    }

    final synchronized void open() {
        isOpen = true;
        onOpen();
    }

    final synchronized void close() {
        if (isOpen) {
            holderIds.clear();
            isOpen = false;
            onClose();
        }
    }


    private void removeUseCaseHolder(UseCaseHolder useCaseHolder) {
        holderIds.remove(useCaseHolder.getUseCaseHolderId());
    }

    void addUseCaseHolder(UseCaseHolder useCaseHolder) {
        holderIds.add(useCaseHolder.getUseCaseHolderId());
    }

    final boolean isOpen() {
        return isOpen;
    }

    public void onHolderDestroy(UseCaseHolder holder) {
        removeUseCaseHolder(holder);
        if (holderIds.size() == 0) {
            close();
        }
    }

    protected abstract void onOpen();

    protected abstract void onClose();

}
