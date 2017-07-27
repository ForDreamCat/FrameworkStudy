package com.bwpsoft.studyframwork.common;


import android.support.annotation.NonNull;

import com.bwpsoft.studyframwork.common.di.ActivityScope;
import com.bwpsoft.studyframwork.common.http.manager.IHttpManager;
import com.bwpsoft.studyframwork.common.loading.ILoadingView;
import com.bwpsoft.studyframwork.common.toast.IToastView;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;


@ActivityScope
public class CommonViewRepository implements ILoadingView, IToastView {

    private final ILoadingView mLoadingView;

    private final IToastView mToastView;

    @Inject
    public CommonViewRepository(@NonNull ILoadingView loadingView,
                                @NonNull IToastView toastView,
                                @NonNull IHttpManager httpView) {
        mLoadingView = checkNotNull(loadingView, "loadingView can not be null");
        mToastView = checkNotNull(toastView, "toastView can not be null");
    }

    @Override
    public void showLoading(boolean cancelable) {
        mLoadingView.showLoading(cancelable);
    }

    @Override
    public void hideLoading() {
        mLoadingView.hideLoading();
    }

    @Override
    public boolean isShowing() {
        return mLoadingView.isShowing();
    }

    @Override
    public void showShortToast(CharSequence text) {
        mToastView.showShortToast(text);
    }

    @Override
    public void showLongToast(CharSequence text) {
        mToastView.showLongToast(text);
    }

    @Override
    public void showShortNewToast(CharSequence text) {
        mToastView.showShortNewToast(text);
    }

    @Override
    public void showLongNewToast(CharSequence text) {
        mToastView.showLongNewToast(text);
    }

}
