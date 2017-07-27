package com.bwpsoft.studyframwork.common.di.moduls;

import android.app.Activity;

import com.bwpsoft.studyframwork.common.di.ActivityScope;
import com.bwpsoft.studyframwork.common.http.manager.HttpManager;
import com.bwpsoft.studyframwork.common.http.manager.IHttpManager;
import com.bwpsoft.studyframwork.common.loading.ILoadingView;
import com.bwpsoft.studyframwork.common.loading.LoadingView;
import com.bwpsoft.studyframwork.common.toast.IToastView;
import com.bwpsoft.studyframwork.common.toast.ToastView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZH on 2017/7/27.
 */
@Module
public class ActivityModule {
private final Activity activity;
    public ActivityModule(Activity activity)
    {
        this.activity=activity;
    }

    @Provides
    @ActivityScope
    Activity provideActivity()
    {
        return this.activity;
    }
    @Provides
    @ActivityScope
    ILoadingView provideLodingView(LoadingView loadingView)
    {
        return loadingView;
    }

    @Provides
    @ActivityScope
    IToastView provideToastView(ToastView toastView)
    {
        return toastView;
    }

    @Provides
    @ActivityScope
    IHttpManager provideHttpManger(HttpManager httpManager)
    {
        return httpManager;
    }
}
