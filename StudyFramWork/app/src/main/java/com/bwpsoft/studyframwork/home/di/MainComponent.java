package com.bwpsoft.studyframwork.home.di;

import com.bwpsoft.studyframwork.common.di.ActivityScope;
import com.bwpsoft.studyframwork.common.di.components.ApplicationComponent;
import com.bwpsoft.studyframwork.common.di.moduls.ActivityModule;
import com.bwpsoft.studyframwork.home.MainActivity;

import dagger.Component;

/**
 * Created by ZH on 2017/7/27.
 */
@ActivityScope
@Component(dependencies = {ApplicationComponent.class},modules = {ActivityModule.class,MainPresenterModule.class})
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
