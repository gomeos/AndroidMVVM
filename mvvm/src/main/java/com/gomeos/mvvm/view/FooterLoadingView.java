package com.gomeos.mvvm.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by liuyuxuan on 16/8/22.
 */
public abstract class FooterLoadingView extends FrameLayout {

    public FooterLoadingView(Context context) {
        super(context);
    }

    public FooterLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FooterLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public abstract void onLoading(boolean isLoading);


}
