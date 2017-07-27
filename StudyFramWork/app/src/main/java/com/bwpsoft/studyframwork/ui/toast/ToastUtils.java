package com.bwpsoft.studyframwork.ui.toast;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by jiangxk on 2016/11/29.
 */

public class ToastUtils {
    private ToastUtils() {

    }

    private static ToastUtils intance_toase;
    private static Toast sToast;

    public static ToastUtils getInstances() {
        if (intance_toase == null) {
            intance_toase = new ToastUtils();
        }
        return intance_toase;
    }

    private static void showToast(Context context, CharSequence text, int duration) {
        if (sToast == null) {
            sToast = Toast.makeText(context.getApplicationContext(), text, duration);
        } else {
            sToast.setText(text);
        }
        sToast.setDuration(duration);
        sToast.setGravity(Gravity.CENTER, 0, 0);
        sToast.show();
    }

    /**
     * 显示短Toast {@link Toast}
     *
     * @param context 上下文
     * @param text    消息
     */
    public static void showShortToast(Context context, CharSequence text) {
        showToast(context, text, Toast.LENGTH_SHORT);
    }

    /**
     * 显示长Toast {@link Toast}
     *
     * @param context 上下文
     * @param text    消息
     */
    public static void showLongToast(Context context, CharSequence text) {
        showToast(context, text, Toast.LENGTH_LONG);
    }
}
