package com.bohdanserdyuk.KVPTPP.di

import com.bohdanserdyuk.KVPTPP.di.modules.*
import com.bohdanserdyuk.KVPTPP.presenter.impl.*
import com.bohdanserdyuk.KVPTPP.view.impl.*
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ContextModule::class,
    DatabaseModule::class,
    DatasourceModule::class,
    ModelsModule::class,
    PresentersModule::class])
@Singleton
interface AppComponent {
    fun inject(activity: ServicesActivity)
    fun inject(activity: PaymentActivity)
    fun inject(fragment: MyPreferenceFragment)
    fun inject(activity: OnceShowingScreen)
    fun inject(activity: SettingsActivity)
    fun inject(activity: SplashScreen)

    fun inject(presenter: ServicesPresenterImpl)
    fun inject(presenter: PaymentPresenterImpl)
    fun inject(presenter: SettingsPresenterImpl)
    fun inject(presenter: OncescreenPresenterImpl)
    fun inject(presenter: SplashPresenterImpl)
}