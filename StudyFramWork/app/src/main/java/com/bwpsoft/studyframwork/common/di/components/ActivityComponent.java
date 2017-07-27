package com.bwpsoft.studyframwork.common.di.components;

import android.app.Activity;

import com.bwpsoft.studyframwork.base.BaseActivity;
import com.bwpsoft.studyframwork.common.di.ActivityScope;
import com.bwpsoft.studyframwork.common.di.moduls.ActivityModule;

import dagger.Component;

/**
 * Created by ZH on 2017/7/27.
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(BaseActivity baseActivity);
    Activity getActivity();
}
