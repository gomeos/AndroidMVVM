package com.gomeos.mvvm.event;

import com.gomeos.activitystarter.ActivityStarter;

/**
 * Created by liuyuxuan on 16/10/8.
 */
public class BroadcastEvent{
    private ActivityStarter activityStarter;

    public ActivityStarter getActivityStarter() {
        return activityStarter;
    }

    public void setActivityStarter(ActivityStarter activityStarter) {
        this.activityStarter = activityStarter;
    }
}
