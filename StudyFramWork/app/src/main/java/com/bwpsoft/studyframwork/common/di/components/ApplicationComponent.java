package com.bwpsoft.studyframwork.common.di.components;

import android.content.Context;

import com.bwpsoft.studyframwork.common.di.moduls.ApiModule;
import com.bwpsoft.studyframwork.common.di.moduls.ApplicationModule;
import com.bwpsoft.studyframwork.data.login.LoginRepository;
import com.bwpsoft.studyframwork.myapplication.AndroidApplication;
import com.bwpsoft.studyframwork.utils.handler.IJsonHandler;
import com.bwpsoft.studyframwork.utils.schedulers.ISchedulerProvider;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ZH on 2017/7/27.
 */
@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface ApplicationComponent {
    void inject(AndroidApplication application);

    /**
     * @return 上下文
     */
    Context getContext();


    /**
     * @return 线程调度器
     */
    ISchedulerProvider getSchedulerProvider();




    /**
     * @return Json处理
     */
    IJsonHandler getJsonHandler();

    /**
     * @return 用户管理
     */
    LoginRepository getLoginRepository();


}
