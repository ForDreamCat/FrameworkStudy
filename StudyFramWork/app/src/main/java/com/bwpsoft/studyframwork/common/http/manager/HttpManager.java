package com.bwpsoft.studyframwork.common.http.manager;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

import com.bwpsoft.studyframwork.R;
import com.bwpsoft.studyframwork.common.di.ActivityScope;

import javax.inject.Inject;

/**
 * Created by jiangxk on 2016/11/29.
 */
@ActivityScope
public class HttpManager implements IHttpManager {
    private static final String TAG = "HttpManager";
    private Context mContext;

    @Inject
    public HttpManager(Activity activity) {
        this.mContext = activity;
    }

    @Override
    public void showNetworkFailureDialog() {
        new AlertDialog.Builder(mContext)
                .setMessage(R.string.network_failure)
                .setPositiveButton(R.string.go_setting, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss(); //关闭dialog
                        openSystemNetworkSetting();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    private void openSystemNetworkSetting() {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        mContext.startActivity(intent);
    }


    @Override
    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
