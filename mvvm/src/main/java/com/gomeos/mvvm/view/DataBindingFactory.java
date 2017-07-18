package com.gomeos.mvvm.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.gomeos.mvvm.view.ui.BaseActivity;
import com.gomeos.mvvm.utils.CheckUtils;
import com.gomeos.mvvm.R;
import com.gomeos.mvvm.viewmodel.Lifecycle;
import com.gomeos.mvvm.viewmodel.ViewModel;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liuyuxuan on 16/8/18.
 */
public class DataBindingFactory {

    public static <T extends ViewDataBinding> T setContentView(BaseActivity baseActivity, @LayoutRes int layout) {
        final T value = DataBindingUtil.setContentView(baseActivity, layout);
        value.getRoot().addOnAttachStateChangeListener(createOnAttachStateChangeListener(value));
        return value;
    }

    @NonNull
    public static List<ViewModel> getViewModelsFromDataBinding(ViewDataBinding viewDataBinding) {
        return findByClass(viewDataBinding, ViewModel.class);
    }

    @NonNull
    private static List<Lifecycle> getLifecycleListFromDataBinding(ViewDataBinding viewDataBinding) {
        return findByClass(viewDataBinding, Lifecycle.class);
    }

    @NonNull
    private static <T> List<T> findByClass(ViewDataBinding viewDataBinding, Class<T> clazz) {
        List<T> list = new LinkedList<>();
        try {
            Field[] fields = viewDataBinding.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object obj = field.get(viewDataBinding);
                if (clazz.isInstance(obj)) {
                    list.add(clazz.cast(obj));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static final <T extends ViewDataBinding> T inflate(Context context, @LayoutRes int layoutId) {
        final T value = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, null, false);
        value.getRoot().addOnAttachStateChangeListener(createOnAttachStateChangeListener(value));
        return value;
    }

    public static final <T extends ViewDataBinding> T bind(@NonNull View view) {
        final T value = DataBindingUtil.bind(view);
        value.getRoot().addOnAttachStateChangeListener(createOnAttachStateChangeListener(value));
        return value;
    }

    private static final <T extends ViewDataBinding> View.OnAttachStateChangeListener createOnAttachStateChangeListener(final T value) {

        View.OnAttachStateChangeListener listener = new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View view) {
                List<Lifecycle> lifecycleList = getLifecycleListFromDataBinding(value);
                for (Lifecycle lifecycle : lifecycleList) {
                    lifecycle.attachedToView();
                }
            }

            @Override
            public void onViewDetachedFromWindow(View view) {
                List<Lifecycle> lifecycleList = getLifecycleListFromDataBinding(value);
                for (Lifecycle lifecycle : lifecycleList) {
                    lifecycle.detachedFromView();
                }
            }
        };
        value.getRoot().setTag(R.integer.databinding_factory_key, listener);
        return listener;
    }

    public static void checkViewDataBinding(View view) {
        if (view == null) {
            return;
        }
        ViewDataBinding viewDataBinding = DataBindingUtil.findBinding(view);
        if (viewDataBinding != null) {
            Object obj = viewDataBinding.getRoot().getTag(R.integer.databinding_factory_key);
            CheckUtils.checkNotNull(obj);
        }
    }

}
