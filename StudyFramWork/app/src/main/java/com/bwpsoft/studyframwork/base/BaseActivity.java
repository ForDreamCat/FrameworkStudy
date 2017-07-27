package com.bwpsoft.studyframwork.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bwpsoft.studyframwork.R;
import com.bwpsoft.studyframwork.common.CommonViewRepository;
import com.bwpsoft.studyframwork.common.di.components.ActivityComponent;
import com.bwpsoft.studyframwork.common.di.components.ApplicationComponent;
import com.bwpsoft.studyframwork.common.di.components.DaggerActivityComponent;
import com.bwpsoft.studyframwork.common.di.moduls.ActivityModule;
import com.bwpsoft.studyframwork.myapplication.AndroidApplication;
import com.bwpsoft.studyframwork.utils.ActivityCollector;


import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.Lazy;

/**
 * BaseActivity for every Activity in this application.
 */
public class BaseActivity extends AppCompatActivity implements BaseView {
    @Inject
    Lazy<CommonViewRepository> mCommonViewRepositoryLazy;

    private ActivityModule mActivityModule;


    //region Activity dependence injector
    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link ApplicationComponent}
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    /**
     * Get an Activity module for dependency injection.
     *
     * @return {@link ActivityModule}
     */
    protected ActivityModule getActivityModule() {
        return mActivityModule;
    }

    //endregion

    //region Activity Lifecycle
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        initActivityComponent();
    }

    private void initActivityComponent() {
        mActivityModule = new ActivityModule(this);
        DaggerActivityComponent
                .builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(mActivityModule)
                .build()
                .inject(this);
    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        try {
//            MobclickAgent.onResume(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
//            MobclickAgent.onPause(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //endregion


    //region Common method that all activity can use

    /**
     * default set cancelable true {@link #showLoadingDialog(boolean)}
     */
    protected void showLoading() {
        showLoadingDialog(false);
    }


    @Override
    public void showNetWorkError() {
        showShortToast(getString(R.string.network_error));
    }

    @Override
    public void showErrorMessage(String message) {
        showShortToast(message);
    }



    /**
     * 显示加载框
     *
     * @param cancelable 是否可以取消
     */
    @Override
    public void showLoadingDialog(boolean cancelable) {
        if (!isFinishing() && mCommonViewRepositoryLazy != null) {
            getCommonViewRepositoryLazy().showLoading(cancelable);
        }
    }

    private CommonViewRepository getCommonViewRepositoryLazy() {
        return mCommonViewRepositoryLazy.get();
    }


    /**
     * 隐藏加载框
     */
    public void hideLoadingDialog() {
        if (!isFinishing() && mCommonViewRepositoryLazy != null && getCommonViewRepositoryLazy().isShowing()) {
            getCommonViewRepositoryLazy().hideLoading();
        }
    }

    @Override
    public void finishActivity() {
        finish();
    }

    public boolean isShowing() {
        if (!isFinishing() && mCommonViewRepositoryLazy != null) {
            return getCommonViewRepositoryLazy().isShowing();
        }
        return false;
    }


    /**
     * 提示短时长信息 {@link android.widget.Toast}
     *
     * @param text 信息
     */
    protected void showShortToast(CharSequence text) {
        if (mCommonViewRepositoryLazy != null && !isFinishing()) {
            getCommonViewRepositoryLazy().showShortToast(text);
        }
    }

    /**
     * 提示短时长信息 {@link android.widget.Toast}
     *
     * @param resId string——id
     */
    protected void showShortToast(int resId) {
        showShortToast(getString(resId));
    }


    /**
     * 提示长时长信息 {@link android.widget.Toast}
     *
     * @param text 信息
     */
    protected void showLongToast(CharSequence text) {
        if (mCommonViewRepositoryLazy != null && !isFinishing()) {
            getCommonViewRepositoryLazy().showLongToast(text);
        }
    }


    /**
     * 提示短时长信息 {@link android.widget.Toast}
     *
     * @param text 信息
     */
    protected void showShortNewToast(CharSequence text) {
        if (mCommonViewRepositoryLazy != null && !isFinishing()) {
            getCommonViewRepositoryLazy().showShortNewToast(text);
        }
    }


    /**
     * 提示长时长信息 {@link android.widget.Toast}
     *
     * @param text 信息
     */
    protected void showLongNewToast(CharSequence text) {
        if (mCommonViewRepositoryLazy != null && !isFinishing()) {
            getCommonViewRepositoryLazy().showLongNewToast(text);
        }
    }

    protected void intent2Activity(Class<? extends Activity> classOfTargetActivity) {
        startActivity(new Intent(this, classOfTargetActivity));
    }

    /**
     * @param toolbar 标题栏
     * @param title   标题内容
     */
    protected void initToolbar(Toolbar toolbar, String title, View.OnClickListener listener) {
        if (toolbar == null) {
            return;
        }
        TextView tvTitle = findById(toolbar, R.id.tv_toolbar_title);
        if (tvTitle != null) {
            tvTitle.setText(title == null ? "" : title);
        }
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        if (listener != null) {
            toolbar.setNavigationIcon(R.drawable.back);
            toolbar.setNavigationOnClickListener(listener);
        }
    }

    protected <T extends View> T findById(@NonNull View view, @IdRes int resId) {
        return ButterKnife.findById(view, resId);
    }
    //endregion


}
