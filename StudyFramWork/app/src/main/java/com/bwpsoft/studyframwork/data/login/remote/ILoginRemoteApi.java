package com.bwpsoft.studyframwork.data.login.remote;

import io.reactivex.Observable;

/**
 * Created by ZH on 2017/7/27.
 */

public interface ILoginRemoteApi {


    /**
     * 登录接口
     */
    String LOGIN_METHOD = "/MainSystem/MainSystem/Auth/Login";

    /**
     * ERP登录
     *
     * @param userName 用户名
     * @param password 密码
     */
    Observable<Object> loginRemote(String userName, String password);

    /**
     * 退出接口
     */
    String LOGOUT_METHOD = "/MainSystem/MainSystem/Auth/Logout";

    /**
     * ERP退出
     */
    Observable<Object> logoutRemote();
}
