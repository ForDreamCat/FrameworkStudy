package com.bwpsoft.studyframwork.common.http.manager;

import android.support.annotation.NonNull;

import com.bwpsoft.studyframwork.utils.LogUtils;
import com.bwpsoft.studyframwork.utils.exception.MyRuntimeException;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

/**
 * Created by jiangxk on 2016/12/19.
 */

public class ResponseHandler {
    private static final String TAG = "ResponseHandler";
    private static final Gson GSON = new Gson();

    public static <T> T fromJson(String json, Class<T> response){
        try {
            return GSON.fromJson(json, response);
        } catch (JsonSyntaxException e) {
            LogUtils.e(TAG, "fromJson: Json to Object (" + response.getSimpleName() + ") with error");
            return null;
        }
    }

    public static <T> T fromJson(String json, Type typeOfT) throws MyRuntimeException {
        try {
            return GSON.fromJson(json, typeOfT);
        } catch (JsonSyntaxException e) {
            LogUtils.e(TAG, "fromJson: Json to Object (" + typeOfT.toString() + ") with error");
            throw new MyRuntimeException("Json to Object (" + typeOfT.toString() + ") with error", e);
        }
    }

    public static String toJson(@NonNull Object object) throws MyRuntimeException {
        try {
            return GSON.toJson(object);
        } catch (Exception e) {
            LogUtils.e(TAG, "toJson: Object (" + object.getClass().getSimpleName() + ") to Json with error");
            throw new MyRuntimeException("Object (" + object.getClass().getSimpleName() + ") to Json with error", e);
        }
    }


}
