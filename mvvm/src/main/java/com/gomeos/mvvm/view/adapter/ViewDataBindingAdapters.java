package com.gomeos.mvvm.view.adapter;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.gomeos.mvvm.viewmodel.command.OnCheckedChangeCommand;
import com.gomeos.mvvm.viewmodel.command.OnClickCommand;
import com.gomeos.mvvm.viewmodel.command.OnLongCommand;

/**
 * Created by liuyuxuan on 16/8/23.
 */
public class ViewDataBindingAdapters {

    @BindingConversion
    public static View.OnClickListener click(final OnClickCommand onClickCommand) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickCommand != null) {
                    onClickCommand.execute(view.getId());
                }
            }
        };
    }

    @BindingAdapter("onLongClick")
    public static void longClick(View view, final OnLongCommand onLongCommand) {
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onLongCommand != null) {
                    return onLongCommand.onLongCommand(v.getId());
                }

                return false;
            }
        });
    }

    @BindingAdapter("onCheckedChange")
    public static void checkedChange(CompoundButton view, final OnCheckedChangeCommand onLongCommand) {
        view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (onLongCommand != null) {
                    onLongCommand.execute(buttonView.getId(), isChecked);
                }
            }
        });
    }

    @BindingConversion
    public static String classConversion(Class className) {
        return className.getName();
    }


    @BindingAdapter("showOrHide")
    public static void showOrHide(View view, boolean showOrHide) {
        view.setVisibility(showOrHide ? View.VISIBLE : View.GONE);
    }


    @BindingAdapter("android:src")
    public static void adaptSrcToRes(final View view, final int resId) {
        if (view instanceof ImageView) {
            ((ImageView) view).setImageResource(resId);
        }
    }

    @BindingAdapter("android:visibility")
    public static void adaptVisibility(final View view, final boolean isVisible) {
        if (view != null) {
            view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        }
    }
}
