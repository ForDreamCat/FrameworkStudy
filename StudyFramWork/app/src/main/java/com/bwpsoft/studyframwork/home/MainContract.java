package com.bwpsoft.studyframwork.home;

import com.bwpsoft.studyframwork.base.BasePresenter;
import com.bwpsoft.studyframwork.base.BaseView;

/**
 * Created by ZH on 2017/7/27.
 */

public interface MainContract {
    interface View extends BaseView
    {
       void isLogin(boolean isLogin);
    }
    interface Presenter extends BasePresenter
    {
        void login(String username,String password);
    }
}
