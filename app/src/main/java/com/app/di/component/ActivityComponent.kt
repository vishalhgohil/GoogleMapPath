package com.app.di.component

import com.app.di.module.ActivityModule
import com.app.di.module.FragmentModule
import com.app.ui.home.IsolatedFullActivity
import com.app.ui.base.BaseActivity
import com.app.ui.home.HomeActivity
import com.app.ui.manager.Navigator
import dagger.BindsInstance
import dagger.Component

@com.app.di.PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun activity(): BaseActivity

    fun navigator(): Navigator


    operator fun plus(fragmentModule: FragmentModule): FragmentComponent

    fun inject(mainActivity: HomeActivity)
    fun inject(isolatedFullActivity: IsolatedFullActivity)

    @Component.Builder
    interface Builder {

        fun bindApplicationComponent(applicationComponent: ApplicationComponent): Builder

        @BindsInstance
        fun bindActivity(baseActivity: BaseActivity): Builder

        fun build(): ActivityComponent
    }
}
