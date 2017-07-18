package com.gomeos.mvvm.event;

import com.gomeos.mvvm.view.ui.ActivityInfo;

/**
 * Created by liuyuxuan on 16/8/15.
 */
public class Events {
    public static class ActivityDestroyEvent  {
        private final ActivityInfo activityInfo;

        public ActivityDestroyEvent(ActivityInfo activityInfo) {
            this.activityInfo = activityInfo;
        }

        public ActivityInfo getActivityInfo() {
            return activityInfo;
        }
    }

}
