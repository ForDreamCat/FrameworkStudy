package com.bwpsoft.studyframwork.common.loading;

import android.app.Activity;

import com.bwpsoft.studyframwork.R;
import com.bwpsoft.studyframwork.common.di.ActivityScope;
import com.bwpsoft.studyframwork.ui.dialog.LoadingDialog;

import javax.inject.Inject;

/**
 * 实现提示加载中Dialog {@link ILoadingView}
 */
@ActivityScope
public class LoadingView implements ILoadingView {
    private LoadingDialog mLoadingDialog;

    @Inject
    public LoadingView(Activity activity) {
        mLoadingDialog = new LoadingDialog(activity, R.style.Loading_Dialog);
    }

    @Override
    public void showLoading(boolean cancelable) {
        if (mLoadingDialog != null) {
            if (!mLoadingDialog.isShowing()) {
                mLoadingDialog.show();
            }
            mLoadingDialog.setCancelable(cancelable);
        }
    }

    @Override
    public void hideLoading() {
        if (mLoadingDialog != null) {
            if (mLoadingDialog.isShowing()) {
                mLoadingDialog.dismiss();
            }
        }
    }

    @Override
    public boolean isShowing() {
        return mLoadingDialog != null && mLoadingDialog.isShowing();
    }
}
