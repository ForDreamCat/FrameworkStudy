package com.bwpsoft.studyframwork.common.rx;


import com.bwpsoft.studyframwork.model.BaseERPModel;
import com.bwpsoft.studyframwork.utils.exception.MyRuntimeException;
import com.bwpsoft.studyframwork.utils.handler.IJsonHandler;

import java.lang.reflect.Type;
import io.reactivex.functions.Function;
import static com.google.common.base.Preconditions.checkNotNull;

public class StringToResultFunction<T> implements Function<String, T> {

    private IJsonHandler mJsonHandler;
    private Type mType;


    public StringToResultFunction(IJsonHandler jsonHandler, Type type) {
        this.mJsonHandler = checkNotNull(jsonHandler);
        this.mType = type;
    }

    @Override
    public T apply(String json) throws Exception {
        BaseERPModel<T> baseERPModel = mJsonHandler.fromJson(json, mType);
        if (baseERPModel == null) {
            throw new MyRuntimeException("获取数据为空");
        }
        if (!baseERPModel.isSuccessful()) {
            throw new MyRuntimeException(baseERPModel.getError().getMessage());
        }

        T t = baseERPModel.getResult();
        if (t == null) {
            t = (T) "";
        }
        return t;
    }
}
