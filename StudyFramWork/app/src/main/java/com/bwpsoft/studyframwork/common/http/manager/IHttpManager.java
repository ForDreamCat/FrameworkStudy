package com.bwpsoft.studyframwork.common.http.manager;

/**
 * Created by jiangxk on 2016/11/29.
 */

public interface IHttpManager {
    /**
     * 显示网络错误Dialog
     */
    void showNetworkFailureDialog();

    /**
     * 判断网络是否可用
     *
     * @return 是否可用
     */
    boolean isOnline();
}
