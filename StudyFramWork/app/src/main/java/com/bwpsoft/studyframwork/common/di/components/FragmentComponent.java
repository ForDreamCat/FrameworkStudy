package com.bwpsoft.studyframwork.common.di.components;

import com.bwpsoft.studyframwork.common.di.FragmentScope;
import com.bwpsoft.studyframwork.common.di.moduls.FragmentModule;
import dagger.Component;


@FragmentScope
@Component(dependencies = ApplicationComponent.class, modules = {FragmentModule.class})
public interface FragmentComponent {

}
