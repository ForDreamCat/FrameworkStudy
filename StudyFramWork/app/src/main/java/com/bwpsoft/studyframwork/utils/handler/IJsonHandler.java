package com.bwpsoft.studyframwork.utils.handler;

import android.support.annotation.NonNull;

import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

/**
 * Created by jiangxk on 2016/11/29.
 */

public interface IJsonHandler {
    <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException;

    <T> T fromJson(String json, Type classOfT) throws JsonSyntaxException;

    String toJson(@NonNull Object object);
}
