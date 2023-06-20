package com.app.di.module

import com.app.di.PerActivity
import com.app.ui.base.BaseActivity
import com.app.ui.manager.FragmentHandler
import com.app.ui.manager.Navigator

import javax.inject.Named

import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    @Provides
    @com.app.di.PerActivity
    internal fun navigator(activity: BaseActivity): Navigator {
        return activity
    }

    @Provides
    @com.app.di.PerActivity
    internal fun fragmentManager(baseActivity: BaseActivity): androidx.fragment.app.FragmentManager {
        return baseActivity.supportFragmentManager
    }

    @Provides
    @com.app.di.PerActivity
    @Named("placeholder")
    internal fun placeHolder(baseActivity: BaseActivity): Int {
        return baseActivity.findFragmentPlaceHolder()
    }

    @Provides
    @com.app.di.PerActivity
    internal fun fragmentHandler(fragmentManager: com.app.ui.manager.FragmentManager): FragmentHandler {
        return fragmentManager
    }


}
