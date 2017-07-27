package com.bwpsoft.studyframwork.home;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.bwpsoft.studyframwork.R;
import com.bwpsoft.studyframwork.base.BaseActivity;
import com.bwpsoft.studyframwork.home.di.DaggerMainComponent;
import com.bwpsoft.studyframwork.home.di.MainPresenterModule;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainContract.View {

    @Bind(R.id.username)
    EditText username;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.login)
    Button login;
    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initInjector();
    }

    @Override
    public void isLogin(boolean isLogin) {
        if (isLogin) {
            showShortToast("登录成功");
        } else {
            showShortToast("登录失败");
        }
    }

    private void initInjector() {
        DaggerMainComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .mainPresenterModule(new MainPresenterModule(this))
                .build()
                .inject(this);
    }

    @OnClick(R.id.login)
    public void onViewClicked() {
        String usernames = username.getText().toString();
        String pwd = password.getText().toString();
        showShortToast("username=="+usernames+"--------"+pwd);
        presenter.login(username.getText().toString(), password.getText().toString());
    }
}
