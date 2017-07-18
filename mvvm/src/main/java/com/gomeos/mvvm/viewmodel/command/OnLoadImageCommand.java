package com.gomeos.mvvm.viewmodel.command;

/**
 * Created by liuyuxuan on 2016/11/7.
 */

public interface OnLoadImageCommand {

    void onLoadSuccess(int id, int w, int h);

    void onLoadFailure(int id, Throwable throwable);
}
