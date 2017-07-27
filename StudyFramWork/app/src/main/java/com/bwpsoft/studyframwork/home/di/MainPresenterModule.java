package com.bwpsoft.studyframwork.home.di;

import com.bwpsoft.studyframwork.common.di.ActivityScope;
import com.bwpsoft.studyframwork.home.MainContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZH on 2017/7/27.
 */
@Module
public class MainPresenterModule {
    private final MainContract.View mView;
    public MainPresenterModule(MainContract.View view)
    {
        mView=view;
    }
    @ActivityScope
    @Provides
    MainContract.View providerMainView()
    {
        return mView;
    }
}
