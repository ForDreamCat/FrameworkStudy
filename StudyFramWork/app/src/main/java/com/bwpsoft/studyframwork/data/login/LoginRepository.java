package com.bwpsoft.studyframwork.data.login;

import android.support.annotation.NonNull;

import com.bwpsoft.studyframwork.common.di.ActivityScope;
import com.bwpsoft.studyframwork.data.login.local.ILoginLocalApi;
import com.bwpsoft.studyframwork.data.login.remote.ILoginRemoteApi;
import com.bwpsoft.studyframwork.utils.handler.IJsonHandler;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ZH on 2017/7/27.
 */
@Singleton
public class LoginRepository implements ILoginRemoteApi,ILoginLocalApi {
    private ILoginRemoteApi iLoginRemoteApi;
    private IJsonHandler mJsonHandle;

    @Inject
    public LoginRepository(@NonNull ILoginRemoteApi iLoginRemoteApi,
                           @NonNull IJsonHandler jsonHandler) {
        this.iLoginRemoteApi = checkNotNull(iLoginRemoteApi);
        this.mJsonHandle = checkNotNull(jsonHandler);
    }

    @Override
    public Observable<Object> loginRemote(String userName, String password) {
        return iLoginRemoteApi.loginRemote(userName, password);
    }

    @Override
    public Observable<Object> logoutRemote() {
        return null;
    }

    @Override
    public void logoutLocal() {

    }
}
