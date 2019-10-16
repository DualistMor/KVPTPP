package com.bohdanserdyuk.KVPTPP.di

import com.bohdanserdyuk.KVPTPP.di.modules.*
import com.bohdanserdyuk.KVPTPP.view.impl.*
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ContextModule::class,
    DatabaseModule::class,
    DatasourceModule::class,
    ModelsModule::class,
    PresentersModule::class,
    HelpersModule::class])
@Singleton
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: RequestFragment)
    fun inject(fragment: AboutFragment)
    fun inject(fragment: ServicesFragment)
    fun inject(fragment: PaymentFragment)
    fun inject(fragment: SettingsFragment)
    fun inject(activity: OnceShowingScreen)
    fun inject(activity: SplashScreen)
}