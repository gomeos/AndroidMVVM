package com.gomeos.mvvm.viewmodel;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by liuyuxuan on 16/8/18.
 */
public interface Lifecycle {

    void attachedToView();

    void detachedFromView();

    void onWindowFocusChanged(boolean hasFocus);

    void onSaveInstanceState(Bundle bundle);

    void onRestoreInstanceState(Bundle bundle);

    void setUserVisibleHint(boolean isVisibleToUser);

    void onActivityResult(int requestCode, int resultCode, Intent intent);

    void accept(Visitor viewModelVisitor);

    void create(Bundle bundle);

    void start();

    void restart();

    void stop();

    void resume();

    void pause();
}