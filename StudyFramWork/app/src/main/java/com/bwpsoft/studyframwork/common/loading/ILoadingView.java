package com.bwpsoft.studyframwork.common.loading;

/**
 * Created by jiangxk on 2016/11/30.
 */

public interface ILoadingView {

    /**
     * 显示加载弹框
     *
     * @param cancelable 是否可以取消
     */
    void showLoading(boolean cancelable);

    /**
     * 隐藏加载弹框
     */
    void hideLoading();

    /**
     * 是否正在显示
     *
     * @return 是否正在显示
     */
    boolean isShowing();
}
