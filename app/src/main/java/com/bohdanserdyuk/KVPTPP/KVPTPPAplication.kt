package com.bohdanserdyuk.KVPTPP

import android.app.Application
import com.bohdanserdyuk.KVPTPP.di.AppComponent
import com.bohdanserdyuk.KVPTPP.di.DaggerAppComponent
import com.bohdanserdyuk.KVPTPP.di.modules.ContextModule

class KVPTPPAplication: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().contextModule(ContextModule(applicationContext)).build()
    }
}