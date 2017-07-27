package com.bwpsoft.studyframwork.common.di.moduls;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.bwpsoft.studyframwork.common.di.FragmentScope;

import dagger.Module;
import dagger.Provides;


@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }
}
