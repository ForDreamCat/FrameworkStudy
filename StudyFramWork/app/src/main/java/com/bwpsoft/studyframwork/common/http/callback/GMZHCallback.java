package com.bwpsoft.studyframwork.common.http.callback;


import com.bwpsoft.studyframwork.model.ErrorModel;

/**
 * Created by jiangxk on 2016/12/19.
 */

public abstract class GMZHCallback<T> {

    /**
     * 网络连接失败
     */
    public abstract void onNetWorkError();

    /**
     * 请求失败
     *
     * @param errorModel 失败原因(参数错误，操作失败等)
     */
    public abstract void onResponseError(ErrorModel errorModel);

    /**
     * 网络连接和请求都成功
     *
     * @param result 返回的对象
     */
    public abstract void onResponse(T result);

    /**
     * 网络连接和请求都成功 子线程
     *
     * @param result 返回的对象
     */
    public void onThreadResponse(T result) {
    }

    /**
     * 网络连接和请求都成功 子线程
     *
     * @param json 返回的对象
     */
    public void onResponseJson(String json) {
    }

    /**
     * cookie失效
     */
    public void onUnauthorized() {

    }

}
