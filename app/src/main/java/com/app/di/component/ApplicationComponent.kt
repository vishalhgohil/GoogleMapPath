package com.app.di.component

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import android.content.Context
import android.content.res.Resources
import com.app.core.Session
import com.app.data.repository.UserRepository
import com.app.di.App
import com.app.di.module.ApplicationModule
import com.app.di.module.ServiceModule
import com.app.di.module.ViewModelModule
import com.app.utils.Validator
import dagger.BindsInstance
import dagger.Component
import java.io.File
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class, ServiceModule::class])
interface ApplicationComponent {

    fun context(): Context

    @Named("cache")
    fun provideCacheDir(): File

    fun provideResources(): Resources

    fun provideCurrentLocale(): Locale

    fun provideViewModelFactory(): ViewModelProvider.Factory

    fun inject(appShell: App)

    fun provideValidator():Validator

    fun provideSession(): Session

    @Component.Builder
    interface ApplicationComponentBuilder {

        @BindsInstance
        fun apiKey(@Named("api-key") apiKey: String): ApplicationComponentBuilder

        @BindsInstance
        fun application(application: Application): ApplicationComponentBuilder

        fun build(): ApplicationComponent
    }

}
