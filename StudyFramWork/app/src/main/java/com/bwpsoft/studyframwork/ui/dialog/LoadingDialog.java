package com.bwpsoft.studyframwork.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.bwpsoft.studyframwork.R;

/**
 * Created by jiangxk on 2016/11/17.
 */

public class LoadingDialog extends Dialog {
    public LoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
    }
}
