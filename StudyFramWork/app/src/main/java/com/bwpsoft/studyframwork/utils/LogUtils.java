package com.bwpsoft.studyframwork.utils;

import android.util.Log;

import com.orhanobut.logger.Logger;

/**
 * Created by jiangxk on 2016/11/30.
 */

public class LogUtils {
    private static final int VERBOSE = 1;
    private static final int DEBUG = 2;
    private static final int INFO = 3;
    private static final int WARN = 4;
    private static final int ERROR = 5;
    private static final int NOTHING = 6;

    private static final int LEVEL = DebugConfig.isDebug() ? VERBOSE : NOTHING;


    public static void v(String tag, String msg) {
        if (LEVEL <= VERBOSE) {
            Logger.t(tag).v(msg);
        }
    }

    public static void d(String tag, String msg) {
        if (LEVEL <= DEBUG) {
            Logger.t(tag).d(msg);
        }
    }

    public static void i(String tag, String msg) {
        if (LEVEL <= INFO) {
//            Logger.t(tag).i(msg);
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (LEVEL <= WARN) {
            Logger.t(tag).w(msg);
        }
    }

    public static void e(String tag, String msg) {
        if (LEVEL <= ERROR) {
            Log.e(tag, msg);
        }
    }

    public static void json(String tag, String msg) {
        if (LEVEL <= ERROR) {
            Logger.t(tag).json(msg);
        }
    }

}
