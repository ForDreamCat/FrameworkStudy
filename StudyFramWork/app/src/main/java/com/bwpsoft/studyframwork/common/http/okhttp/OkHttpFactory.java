package com.bwpsoft.studyframwork.common.http.okhttp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bwpsoft.studyframwork.utils.DebugConfig;
import com.bwpsoft.studyframwork.utils.LogUtils;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
/**
 * Created by ZH on 2017/7/27.
 */

public class OkHttpFactory {
    private static final String TAG = "OkHttpFactory";
    private final static int CONNECT_TIMEOUT = 10;
    private final static int WRITE_TIMEOUT = 10;
    private final static int READ_TIMEOUT = 10;

    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    @NonNull
    public static OkHttpClient create(Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new okhttp3.logging.HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtils.i(DebugConfig.TAG_HTTP, message);
            }
        });
        if (DebugConfig.isDebug()) {
            loggingInterceptor.setLevel(okhttp3.logging.HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(okhttp3.logging.HttpLoggingInterceptor.Level.NONE);
        }
        builder.addInterceptor(loggingInterceptor);
//        builder.addInterceptor(CookieInterceptor.getInstances());
        return builder.build();
    }

    /**
     * post 请求数据
     * @param BodyParams
     * @return
     */
    @NonNull
    public RequestBody SetRequestBody(Map<String, String> BodyParams){
        RequestBody body=null;
        MultipartBody.Builder builder=new MultipartBody.Builder();
        if(BodyParams != null){
            Iterator<String> iterator = BodyParams.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                builder.addFormDataPart(key, BodyParams.get(key));
                Log.d("post http", "post_Params==="+key+"===="+BodyParams.get(key));
            }
        }
        body=builder.build();
        return body;
    }

    /**
     * Post上传图片的参数  不带参数
     * @param BodyParams
     * @param fileParams
     * @return
     */
    @NonNull
    public RequestBody SetFileRequestBody(Map<String, String> BodyParams,Map<String, String> fileParams){
        //带文件的Post参数
        RequestBody body=null;
okhttp3.MultipartBody.Builder MultipartBodyBuilder=new okhttp3.MultipartBody.Builder();
        MultipartBodyBuilder.setType(MultipartBody.FORM);
        RequestBody fileBody = null;

        if(BodyParams != null){
            Iterator<String> iterator = BodyParams.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                MultipartBodyBuilder.addFormDataPart(key, BodyParams.get(key));
                Log.d("post http", "post_Params==="+key+"===="+BodyParams.get(key));
            }
        }

        if(fileParams != null){
            Iterator<String> iterator = fileParams.keySet().iterator();
            String key = "";
            int i=0;
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                i++;
                MultipartBodyBuilder.addFormDataPart(key, fileParams.get(key));
                fileBody = RequestBody.create(MediaType.parse("image/*"), new File(fileParams.get(key)));
                MultipartBodyBuilder.addFormDataPart(key, i+".png", fileBody);
            }
        }



        body=MultipartBodyBuilder.build();
        return body;

    }

    /**
     * get方法连接拼加参数
     * @param mapParams
     * @return
     */
    @NonNull
    public String setUrlParams( Map<String, String> mapParams){
        String strParams = "";
        if(mapParams != null){
            Iterator<String> iterator = mapParams.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                strParams += "&"+ key + "=" + mapParams.get(key);
            }
        }

        return strParams;
    }

    /**
     * json 数据上传
     * @param json
     * @return
     */
    @NonNull
    public static RequestBody getRequestBody(String json) {
        LogUtils.i(TAG, json);
        if (json == null) {
            json = "";
        }
        return RequestBody.create(MEDIA_TYPE_JSON, json);
    }
}
