package com.bwpsoft.studyframwork.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangxk on 2016/11/29.
 */

public class ActivityCollector {
    private ActivityCollector() {

    }

    private static List<Activity> sActivityList = new ArrayList<>();

    public static void addActivity(Activity activity) {
        if (!sActivityList.contains(activity)) {
            sActivityList.add(activity);
        }
    }

    public static void removeActivity(Activity activity) {
        sActivityList.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : sActivityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
