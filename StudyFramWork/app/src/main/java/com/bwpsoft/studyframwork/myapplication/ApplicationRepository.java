package com.bwpsoft.studyframwork.myapplication;


import android.content.Context;

import com.bwpsoft.studyframwork.utils.DebugConfig;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ApplicationRepository implements IApplicationRepository {

    private Context mContext;


    @Inject
    public ApplicationRepository(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @Override
    public void init() {


        //初始化Logger
        initializeLogger();


    }

    /**
     * 初始化 logger
     */
    private void initializeLogger() {
        Logger.init(DebugConfig.TAG)
//               .hideThreadInfo()
                .methodOffset(2)
                .logLevel(DebugConfig.isDebug() ? LogLevel.FULL : LogLevel.NONE)
                .methodCount(3);
    }

}
