package com.app.di.module

import com.app.di.PerFragment
import com.app.ui.base.BaseFragment
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val baseFragment: BaseFragment<*>) {

    @Provides
    @com.app.di.PerFragment
    internal fun provideBaseFragment(): BaseFragment<*> {
        return baseFragment
    }

}
