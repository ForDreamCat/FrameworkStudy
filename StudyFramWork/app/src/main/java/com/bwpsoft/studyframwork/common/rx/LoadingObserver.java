package com.bwpsoft.studyframwork.common.rx;

import com.bwpsoft.studyframwork.base.BaseView;
import com.bwpsoft.studyframwork.utils.DebugConfig;
import com.bwpsoft.studyframwork.utils.LogUtils;

import java.net.ConnectException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 封装loading Dialog自动显示和隐藏
 */
public abstract class LoadingObserver<T> implements Observer<T> {
    private BaseView mBaseView;
    private boolean cancelable;

    private boolean isLoading;

    Disposable mDisposable;

    public LoadingObserver(BaseView baseView, boolean cancelable) {
        this.mBaseView = baseView;
        this.cancelable = cancelable;
    }

    public LoadingObserver(BaseView baseView) {
        //默认不可取消
        this(baseView, false);
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (mBaseView != null) {
            isLoading = true;
            mBaseView.showLoadingDialog(cancelable);
        }
        mDisposable = d;
    }

    @Override
    public void onError(Throwable e) {
        if (mBaseView != null) {
            isLoading = false;
            mBaseView.hideLoadingDialog();
        }
        if (e != null && e.getMessage() != null) {
            LogUtils.e(DebugConfig.TAG_HTTP, e.getMessage() + "");
            if (e instanceof ConnectException) {
                if (mBaseView != null) {
                    mBaseView.showNetWorkError();
                }
                onNetworkError();
            } else if (e instanceof ClassCastException || e instanceof NullPointerException) {
                onResultIsNull(e);
            } else {
                if (mBaseView != null) {
                    mBaseView.showErrorMessage(e.getMessage());
                }
                onResponseError(e);
            }
        }
        mDisposable.dispose();
    }

    @Override
    public void onNext(T value) {
        if (mBaseView != null && isLoading) {
            isLoading = false;
            mBaseView.hideLoadingDialog();
        }
        onResponse(value);
    }

    protected abstract void onResponse(T value);


    public void onResultIsNull(Throwable e) {

    }

    public void onResponseError(Throwable e) {

    }

    public void onNetworkError(){}

    @Override
    public void onComplete() {
        if (mBaseView != null && isLoading) {
            isLoading = false;
            mBaseView.hideLoadingDialog();
        }
        mDisposable.dispose();
    }
}
