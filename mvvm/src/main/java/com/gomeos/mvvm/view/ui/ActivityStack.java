package com.gomeos.mvvm.view.ui;

import com.android.annotations.NonNull;
import com.gomeos.activitystarter.ActivityResultManager;
import com.gomeos.mvvm.utils.CheckUtils;
import com.gomeos.mvvm.event.EventProxy;
import com.gomeos.mvvm.event.Events;
import com.orhanobut.logger.Logger;

import java.util.LinkedHashMap;
import java.util.Stack;

/**
 * Created by liuyuxuan on 2016/10/31.
 */
class ActivityStack {
    private static volatile ActivityStack activityStack;

    static ActivityStack getInstance() {
        if (activityStack == null) {
            synchronized (ActivityStack.class) {
                if (activityStack == null) {
                    activityStack = new ActivityStack();
                }
            }
        }
        return activityStack;
    }

    private LinkedHashMap<String, ActivityInfo> activityInfoMap = new LinkedHashMap<>();

    private ActivityStack() {
        EventProxy.getDefault().register(this);
    }

    void onActivityCreate(@NonNull BaseActivity baseActivity) {
        CheckUtils.checkNotNull(baseActivity);
        ActivityInfo activityInfo = activityInfoMap.get(baseActivity.getActivityId());
        if (activityInfo == null) {
            activityInfo = new ActivityInfo(baseActivity);
        }
        activityInfoMap.put(activityInfo.getActivityId(), activityInfo);
        updateActivityInfoState(baseActivity);
    }

    void onActivityStart(@NonNull BaseActivity baseActivity) {
        updateActivityInfoState(baseActivity);
    }

    void onActivityResume(@NonNull BaseActivity baseActivity) {
        updateActivityInfoState(baseActivity);
        Stack<ActivityInfo> stack = new Stack<>();
        stack.addAll(activityInfoMap.values());
        CheckUtils.checkArgument(stack.size() > 0);
        while (!stack.peek().getActivityId().equals(baseActivity.getActivityId())) {
            ActivityInfo activityInfo = stack.pop();
            if (activityInfo.getRunState().equals(RunState.Hibernating)) {
                activityInfo.setRunState(RunState.Destroyed);
                onActivityDestroy(activityInfo);
            }
        }
        Logger.t("ActivityStack").d("onActivityResume \n stack =" + activityInfoMap.values());
    }

    void onActivityPause(@NonNull BaseActivity baseActivity) {
        updateActivityInfoState(baseActivity);
    }

    void onActivityStop(@NonNull BaseActivity baseActivity) {
        updateActivityInfoState(baseActivity);
    }

    void onActivityDestroy(@NonNull BaseActivity baseActivity) {
        updateActivityInfoState(baseActivity);
        onActivityDestroy(getActivityInfo(baseActivity));
    }

    private void onActivityDestroy(@NonNull ActivityInfo activityInfo) {
        Logger.t("ActivityStack").d("onActivityDestroy \n " + activityInfo);
        if (activityInfo.isFinished()) {
            activityInfoMap.remove(activityInfo.getActivityId());
            ActivityResultManager.getInstance().removeActivityResult(activityInfo);
        }
        EventProxy.getDefault().post(new Events.ActivityDestroyEvent(activityInfo));
    }

    private void updateActivityInfoState(@NonNull BaseActivity baseActivity) {
        CheckUtils.checkNotNull(baseActivity);
        ActivityInfo activityInfo = activityInfoMap.get(baseActivity.getActivityId());
        CheckUtils.checkNotNull(activityInfo);
        activityInfo.setRunState(baseActivity.getRunState());
    }

    ActivityInfo getActivityInfo(@NonNull BaseActivity baseActivity) {
        CheckUtils.checkNotNull(baseActivity);
        ActivityInfo activityInfo = activityInfoMap.get(baseActivity.getActivityId());
        CheckUtils.checkNotNull(activityInfo);
        return activityInfo;
    }

}
