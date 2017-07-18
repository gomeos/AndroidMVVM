package com.gomeos.mvvm.viewmodel.command;

/**
 * Created by chenbaocheng on 16/12/14.
 */

public interface OnCheckedChangeCommand {
    void execute(int viewId, boolean isChecked);
}
