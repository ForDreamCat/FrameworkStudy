package com.bwpsoft.studyframwork.myapplication;



import com.bwpsoft.studyframwork.common.di.components.ApplicationComponent;
import com.bwpsoft.studyframwork.common.di.components.DaggerApplicationComponent;
import com.bwpsoft.studyframwork.common.di.moduls.ApiModule;
import com.bwpsoft.studyframwork.common.di.moduls.ApplicationModule;
import com.bwpsoft.studyframwork.common.http.IRestApi;

import org.litepal.LitePalApplication;

import javax.inject.Inject;

/**
 * Created by jiangxk on 2016/11/29.
 */

public class AndroidApplication extends LitePalApplication {
    private static AndroidApplication sApplication;

    /**
     * 单例在ApplicationComponent中声明
     */
    private ApplicationComponent mApplicationComponent;

    @Inject
    IApplicationRepository mApplicationRepository;

    public static AndroidApplication getApplication() {
        return sApplication;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        sApplication = this;
        //初始化依赖注入
        initializeInjector();

        //初始化全局框架
        mApplicationRepository.init();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    private void initializeInjector() {
        this.mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .apiModule(new ApiModule(getBaseApiUrl()))
                .build();
        mApplicationComponent.inject(this);
    }

    private String getBaseApiUrl() {
        return IRestApi.URL_API_ROOT_ERP;
    }

}
