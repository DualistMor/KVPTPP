package com.bohdanserdyuk.KVPTPP.di

import com.bohdanserdyuk.KVPTPP.di.modules.ContextModule
import com.bohdanserdyuk.KVPTPP.di.modules.DatabaseModule
import com.bohdanserdyuk.KVPTPP.di.modules.DatasourceModule
import com.bohdanserdyuk.KVPTPP.di.modules.ModelsModule
import com.bohdanserdyuk.KVPTPP.view.impl.MainActivity
import com.bohdanserdyuk.KVPTPP.view.impl.PaymentActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ContextModule::class,
    DatabaseModule::class,
    DatasourceModule::class,
    ModelsModule::class])
@Singleton
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: PaymentActivity)

}