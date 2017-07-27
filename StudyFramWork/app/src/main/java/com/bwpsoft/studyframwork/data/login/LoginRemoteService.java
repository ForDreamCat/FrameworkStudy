package com.bwpsoft.studyframwork.data.login;


import com.bwpsoft.studyframwork.common.http.IRestApi;
import com.bwpsoft.studyframwork.model.BaseERPModel;

import javax.inject.Singleton;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ZH on 2017/7/27.
 */
@Singleton
public interface LoginRemoteService {
    @POST(IRestApi.URL_API_POST)
    Observable<BaseERPModel<Object>> loginRemote(@Body RequestBody requestBody);
}
