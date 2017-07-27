package com.bwpsoft.studyframwork.home;

import android.support.annotation.NonNull;

import com.bwpsoft.studyframwork.common.rx.LoadingObserver;
import com.bwpsoft.studyframwork.data.login.LoginRepository;
import com.bwpsoft.studyframwork.utils.schedulers.ISchedulerProvider;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ZH on 2017/7/27.
 */

public class MainPresenter implements MainContract.Presenter {
    private LoginRepository mLoginRepository;
    private ISchedulerProvider mSchedulerProvider;
    private MainContract.View mView;

    @Inject
    public MainPresenter(@NonNull LoginRepository loginRepository,
                         @NonNull ISchedulerProvider iSchedulerProvider,
                         @NonNull MainContract.View mView) {
        mLoginRepository = loginRepository;
        mSchedulerProvider = checkNotNull(iSchedulerProvider);
        this.mView = mView;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void login(String username, String password) {
        mLoginRepository.loginRemote(username, password)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new LoadingObserver<Object>(mView)
                {

                    @Override
                    protected void onResponse(Object value) {
                        mView.isLogin(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.isLogin(false);
                    }
                });
    }
}
