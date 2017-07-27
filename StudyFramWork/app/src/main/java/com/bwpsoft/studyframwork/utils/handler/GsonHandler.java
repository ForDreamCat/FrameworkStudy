package com.bwpsoft.studyframwork.utils.handler;

import android.support.annotation.NonNull;

import com.bwpsoft.studyframwork.utils.LogUtils;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by jiangxk on 2016/11/29.
 */
@Singleton
public class GsonHandler implements IJsonHandler {
    private static final String TAG = "GsonHandler";
    private final Gson FROMGSON = new GsonBuilder()
            .create();

    private final Gson TOGSON = new GsonBuilder()
            .setExclusionStrategies(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    return f.getName().startsWith("_id");
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    return false;
                }
            })
            .create();

    @Inject
    public GsonHandler() {

    }

    @Override
    public <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        try {
            return FROMGSON.fromJson(json, classOfT);
        } catch (JsonSyntaxException e) {
            LogUtils.e(TAG, "fromJson: Json to Object (" + classOfT.getSimpleName() + ") with error");
            throw e;
        }
    }

    @Override
    public <T> T fromJson(String json, Type classOfT) throws JsonSyntaxException {
        try {
            return FROMGSON.fromJson(json, classOfT);
        } catch (JsonSyntaxException e) {
            LogUtils.e(TAG, "fromJson: Json to Object (" + classOfT.getClass().getSimpleName() + ") with error");
            throw e;
        }
    }

    @Override
    public String toJson(@NonNull Object object) {
        return TOGSON.toJson(object);
    }
}
