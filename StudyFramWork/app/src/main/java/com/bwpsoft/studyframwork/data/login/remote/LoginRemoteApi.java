package com.bwpsoft.studyframwork.data.login.remote;

import com.bwpsoft.studyframwork.common.http.okhttp.OkHttpFactory;
import com.bwpsoft.studyframwork.common.rx.ResultFunction;
import com.bwpsoft.studyframwork.data.login.LoginRemoteService;
import com.bwpsoft.studyframwork.utils.LogUtils;
import com.bwpsoft.studyframwork.utils.RandomUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * Created by ZH on 2017/7/27.
 */
@Singleton
public class LoginRemoteApi implements  ILoginRemoteApi {
    private static final String TAG = "UserRemoteApi";
    private LoginRemoteService loginRemoteService;
    @Inject
    public LoginRemoteApi(LoginRemoteService loginRemoteService) {
        this.loginRemoteService = loginRemoteService;
    }

    @Override
    public Observable<Object> loginRemote(String userName, String password) {
        LogUtils.i(TAG, "loginRemote: name = " + userName + " password = " + password);
        String json = getJson(LOGIN_METHOD, userName, password);
        RequestBody requestBody = OkHttpFactory.getRequestBody(json);
        return loginRemoteService.loginRemote(requestBody)
                .map(new ResultFunction<>());
    }

    @Override
    public Observable<Object> logoutRemote() {
        return null;
    }

    /**
     * 获取ERP格式Json字符串
     *
     * @param method 接口方法名
     * @param params 参数
     * @return json字符串
     */
    public static String getJson(String method, String... params) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();

        String id = RandomUtils.get1To100RandomInt() + "";
        try {
            jsonObject.put("id", id);
            jsonObject.put("method", method);

            if (params != null) {
                for (String param : params) {
                    jsonArray.put(param);
                }
                jsonObject.put("params", jsonArray);
            }
            LogUtils.i(TAG, jsonObject.toString());
            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }
}
