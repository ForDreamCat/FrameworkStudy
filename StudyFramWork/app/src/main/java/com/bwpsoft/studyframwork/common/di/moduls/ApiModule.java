package com.bwpsoft.studyframwork.common.di.moduls;

import android.content.Context;

import com.bwpsoft.studyframwork.common.di.ResultType;
import com.bwpsoft.studyframwork.common.di.StringType;
import com.bwpsoft.studyframwork.common.http.okhttp.OkHttpFactory;
import com.bwpsoft.studyframwork.common.http.okhttp.RetrofitFactory;
import com.bwpsoft.studyframwork.data.login.LoginRemoteService;
import com.bwpsoft.studyframwork.data.login.remote.ILoginRemoteApi;
import com.bwpsoft.studyframwork.data.login.remote.LoginRemoteApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * 网络框架
 */
@Module
public class ApiModule {


    private final String mBaseApiUrl;

    public ApiModule(String baseApiUrl) {
        mBaseApiUrl = baseApiUrl;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Context context) {
        return OkHttpFactory.create(context);
    }

    @Provides
    @ResultType
    @Singleton
    Retrofit provideResultRetrofit(OkHttpClient okHttpClient) {
        return RetrofitFactory.createGsonWithRxJavaRetrofit(mBaseApiUrl, okHttpClient);
    }

    @Provides
    @StringType
    @Singleton
    Retrofit provideStringRetrofit(OkHttpClient okHttpClient) {
        return RetrofitFactory.createStringWithRxJavaRetrofit(mBaseApiUrl, okHttpClient);
    }

    @Provides
    @Singleton
    LoginRemoteService providerLoginRemote(@ResultType Retrofit retrofit) {
        return retrofit.create(LoginRemoteService.class);
    }


}
