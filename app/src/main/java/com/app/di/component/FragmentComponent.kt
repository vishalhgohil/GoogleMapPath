package com.app.di.component


import com.app.di.PerFragment
import com.app.di.module.FragmentModule
import com.app.ui.base.BaseFragment
import dagger.Subcomponent


@com.app.di.PerFragment
@Subcomponent(modules = [(FragmentModule::class)])
interface FragmentComponent {
    fun baseFragment(): BaseFragment<*>

}
