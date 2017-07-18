package com.gomeos.mvvm.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuyuxuan on 16/4/29.
 */
public class SystemUtils {



    public static final ActivityManager.RunningAppProcessInfo androidProcessInfo(Context context, int pid) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appInfos = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appInfo : appInfos) {
            if (appInfo.pid == pid)
                return appInfo;
        }
        return null;
    }

    /**
     * 是否后台运行
     *
     * @param context
     * @return
     */
    public static boolean isBackgroundRun(Context context) {//  home或者正常退出其实都再运行
        // 返回true
        ActivityManager am = (ActivityManager) context.getSystemService(Activity.ACTIVITY_SERVICE);
        if (am == null)
            return false;

        @SuppressWarnings("deprecation")
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = am.getRunningTasks(1);
        if (runningTaskInfos == null || runningTaskInfos.size() == 0)
            return false;

        String topAppName = null;
        topAppName = runningTaskInfos.get(0).topActivity.getPackageName();
        if (topAppName == null)
            return false;
        if (!topAppName.contains(context.getPackageName()))
            return true;

        return false;
    }

    public static final ApplicationInfo androidApplicationInfo(Context context, int flag) {
        try {
            String packageName = context.getPackageName();
            return context.getPackageManager().getApplicationInfo(packageName, flag);
        } catch (Exception e) {
            return null;
        }
    }


    public static final String androidExternalHome(Context context) {
        File desDir = context.getExternalFilesDir(null);
        if (desDir == null)
            desDir = context.getFilesDir();
        if (desDir == null)
            return null;
        desDir = desDir.getParentFile();
        if (desDir == null)
            return null;
        return desDir.getAbsolutePath();
    }

    public static final PackageInfo androidPackageInfo(Context context, int flags) {
        PackageInfo packageInfo = null;
        try {
            String packageName = context.getPackageName();
            packageInfo = context.getPackageManager().getPackageInfo(packageName, flags);
        } catch (Exception e) {
        }
        return packageInfo;
    }

    public static final boolean androidCheckUsePermission(Context context, String[] permissions) {
        try {
            String packageName = context.getPackageName();
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_PERMISSIONS);
            String[] requestedPermissions = packageInfo.requestedPermissions;
            for (String permission : permissions) {
                boolean ok = false;
                for (String requestedPermission : requestedPermissions) {
                    if (permission.equals(requestedPermission)) {
                        ok = true;
                        break;
                    }
                }
                if (ok == false)
                    return false;
            }

        } catch (Exception e) {
        }
        return true;
    }

    public static final void notificationSend(Context context, int id, int iconRes, String title, boolean autoCancel,
                                              boolean sound, String text, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(iconRes);
        builder.setContentTitle(title);
        builder.setAutoCancel(autoCancel);
        builder.setContentText(text);
        if (sound)
            builder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        if (intent != null) {
            PendingIntent pi = PendingIntent.getActivity(context, 0, intent, 0);
            builder.setContentIntent(pi);
        }
        Notification notification = builder.build();
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(id, notification);
    }

    public static final void notificationSend(Context context, int id, int iconRes, String title, boolean autoCancel,
                                              boolean sound, int total, int progress, boolean indetermined, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(iconRes);
        builder.setContentTitle(title);
        builder.setAutoCancel(autoCancel);
        builder.setProgress(total, progress, indetermined);
        if (sound)
            builder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        if (intent != null) {
            PendingIntent pi = PendingIntent.getActivity(context, 0, intent, 0);
            builder.setContentIntent(pi);
        }
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(id, builder.build());
    }

    public static final void notificationCancel(Context context, int id) {
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(id);
    }


    /**
     * 所有用户自己安装的apk信息
     *
     * @param context
     * @return
     */
    public static List<PackageInfo> getAllInstalledPageages(Context context) {
        ArrayList<PackageInfo> appInstalledByUser = new ArrayList<PackageInfo>();
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> allPackages = packageManager.getInstalledPackages(0);
        for (PackageInfo packageInfo : allPackages) {
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {//由用户安装
                appInstalledByUser.add(packageInfo);
            }
        }
        return appInstalledByUser;
    }

    /**
     * SD卡是否挂载
     *
     * @return
     */
    public static boolean mountedSdCard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
}
