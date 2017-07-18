package com.gomeos.mvvm.view.ui;

import com.gomeos.mvvm.model.UseCaseHolder;
import com.gomeos.activitystarter.ActivityIdentifiable;
import com.gomeos.mvvm.utils.CheckUtils;

import java.lang.ref.WeakReference;

/**
 * Created by liuyuxuan on 2016/10/31.
 */

public class ActivityInfo implements UseCaseHolder, ActivityIdentifiable {
    private String activityId;
    private WeakReference<BaseActivity> activityRef;
    private RunState runState;
    private String activityName;

    @Override
    public String toString() {
        return "{activityName= " + activityName + "} { activityId=" + activityId + "} { runState=" + runState + "}";
    }

    public String getActivityName() {
        return activityName;
    }

    public ActivityInfo(BaseActivity activity) {
        this.activityRef = new WeakReference<>(activity);
        CheckUtils.checkNotNull(activity);
        activityId = activity.getActivityId();
        activityName = activity.getClass().getName();
        runState = activity.getRunState();
    }

    public RunState getRunState() {
        return runState;
    }

    public boolean isFinished() {
        return runState == RunState.Destroyed;
    }

    @Override
    public String getActivityId() {
        return activityId;
    }


    void setRunState(RunState runState) {
        this.runState = runState;
    }

    @Override
    public String getUseCaseHolderId() {
        return activityId;
    }
}
