package com.app.di


import android.app.Application
import com.app.di.component.ApplicationComponent
import com.app.di.component.DaggerApplicationComponent


enum class Injector private constructor() {
    INSTANCE;

    lateinit var applicationComponent: ApplicationComponent
        internal set

    fun initAppComponent(application: Application, apiKey: String) {
        applicationComponent = DaggerApplicationComponent.builder()
                .application(application)
                .apiKey(apiKey)
                .build()
    }
}
