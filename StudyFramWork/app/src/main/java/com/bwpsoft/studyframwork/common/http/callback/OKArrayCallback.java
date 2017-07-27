package com.bwpsoft.studyframwork.common.http.callback;

import android.os.Handler;
import android.os.Looper;

import com.bwpsoft.studyframwork.utils.LogUtils;
import com.bwpsoft.studyframwork.utils.handler.GsonHandler;
import com.bwpsoft.studyframwork.utils.handler.IJsonHandler;
import com.bwpsoft.studyframwork.model.BaseERPModel;
import com.bwpsoft.studyframwork.model.ErrorModel;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by jiangxk on 2016/12/19.
 */

public class OKArrayCallback<T> implements Callback {
    private static final String TAG = "OKCallback";
    private GMZHCallback<List<T>> mCallback;
    private Type mTargetClass;
    private String methodName = "methodName";

    private static Handler sHandler = new Handler(Looper.getMainLooper());

    private IJsonHandler mJsonHandler = new GsonHandler();

    public OKArrayCallback(GMZHCallback<List<T>> callback, Type targetClass, String methodName) {
        this.mCallback = callback;
        this.mTargetClass = targetClass;
        this.methodName = methodName;
    }

    public OKArrayCallback(GMZHCallback<List<T>> callback, Type targetClass) {
        this(callback, targetClass, "methodName");
    }

    @Override
    public void onFailure(Call call, final IOException e) {
        LogUtils.i(TAG, e.getMessage() + "");
        if (call.isCanceled()) {
            return;
        }
        handlerNetWorkError();
    }

    @Override
    public void onResponse(Call call, final Response response) throws IOException {
        if (call.isCanceled()) {
            return;
        }
        if (!response.isSuccessful()) {
            sHandler.post(new Runnable() {
                @Override
                public void run() {
                    LogUtils.e(TAG, methodName + "服务器错误");
                    LogUtils.e(TAG, methodName + ": " + ": Unexpected code " + response);
                    ErrorModel errorModel = new ErrorModel();
                    errorModel.setErrorMessage("服务器错误" + response.message());
                    mCallback.onResponseError(errorModel);
                }
            });
            return;
        }
        final String json = response.body().string();
        LogUtils.i(TAG, methodName + " : " + json);
        mCallback.onResponseJson(json);

        BaseERPModel baseERPModel;
        try {
            baseERPModel = mJsonHandler.fromJson(json, BaseERPModel.class);
        } catch (JsonSyntaxException e) {
            handlerJsonParserError();
            return;
        }

        if (baseERPModel.isUnauthorized()) {
            handlerUnauthorized();
        } else {
            if (baseERPModel.isSuccessful()) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String resultString = jsonObject.get("result").toString();
                    List<T> result = mJsonHandler.fromJson(resultString, mTargetClass);
                    handlerResponse(result);
                } catch (Exception e) {
                    handlerJsonParserError();
                }
            } else {
                handlerResponseError(baseERPModel);
            }
        }
    }

    private void handlerNetWorkError() {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onNetWorkError();
            }
        });
    }

    private void handlerUnauthorized() {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onUnauthorized();
            }
        });
    }

    private void handlerResponse(final List<T> result) {
        mCallback.onThreadResponse(result);
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onResponse(result);
            }
        });
    }

    private void handlerResponseError(final BaseERPModel baseERPModel) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                ErrorModel errorModel = new ErrorModel();
                errorModel.setErrorMessage(baseERPModel.getError().getMessage() + "");
                mCallback.onResponseError(errorModel);
            }
        });
    }

    private void handlerJsonParserError() {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                LogUtils.i(TAG, "数据解析失败");
                ErrorModel errorModel = new ErrorModel();
                errorModel.setErrorMessage("数据解析失败");
                mCallback.onResponseError(errorModel);
            }
        });
    }

}
