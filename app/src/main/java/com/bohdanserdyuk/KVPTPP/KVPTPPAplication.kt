package com.bohdanserdyuk.KVPTPP

import android.app.Application
import android.content.Context
import com.bohdanserdyuk.KVPTPP.di.AppComponent
import com.bohdanserdyuk.KVPTPP.di.DaggerAppComponent
import com.bohdanserdyuk.KVPTPP.di.modules.ContextModule
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import com.squareup.leakcanary.LeakCanary.refWatcher
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class KVPTPPAplication : Application() {

    lateinit var appComponent: AppComponent
    private lateinit var refWatcher: RefWatcher

    override fun onCreate() {
        super.onCreate()
        refWatcher = LeakCanary.install(this)
        appComponent = DaggerAppComponent.builder().contextModule(ContextModule(applicationContext)).build()
    }
    companion object {
        fun getRefWatcher(context: Context): RefWatcher {
            val application = context.applicationContext as KVPTPPAplication
            return application.refWatcher
        }
    }
}