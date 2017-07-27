package com.bwpsoft.studyframwork.common.rx;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by hanzhifengyun on 2017/1/16.
 */

public abstract class BaseObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {

    }
}
