package com.bohdanserdyuk.KVPTPP.di

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.db.LocalDatabase
import com.bohdanserdyuk.KVPTPP.model.repository.ServicesModel
import com.bohdanserdyuk.KVPTPP.model.repository.impl.PreferencesModelImpl
import com.bohdanserdyuk.KVPTPP.presenter.impl.*

internal class DependencyInjectorImpl : DependencyInjector {

    override fun sharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    override fun mainPresenter(preferencesModelImpl: PreferencesModelImpl, servicesModel: ServicesModel): BaseContract.MainPresenter {
       return MainPresenterImpl(preferencesModelImpl, servicesModel)
    }

    override fun paymentPresenter(preferencesModelImpl: PreferencesModelImpl, servicesModel: ServicesModel): BaseContract.PaymentPresenter {
        return PaymentPresenterImpl(preferencesModelImpl, servicesModel)
    }

    override fun settingsPresenter(preferencesModelImpl: PreferencesModelImpl): BaseContract.PreferencesPresenter {
        return SettingsPresenterImpl(preferencesModelImpl)
    }

    override fun splashPresenter(preferencesModelImpl: PreferencesModelImpl): BaseContract.SplashPresenter {
        return SplashPresenterImpl(preferencesModelImpl)
    }

    override fun oncePresenter(preferencesModelImpl: PreferencesModelImpl): BaseContract.OncePresenter {
        return OncePresenterImpl(preferencesModelImpl)
    }

    override fun roomLocalDatabase(context: Context): LocalDatabase {
        return Room.databaseBuilder(context,
            LocalDatabase::class.java, "database").build()
    }

    override fun emptyModelsArray(): Array<BaseContract.Model> {
        return arrayOfNulls<BaseContract.Model>(0) as Array<BaseContract.Model>
    }

    override fun preferencesModelArray(preferencesModelImpl: PreferencesModelImpl): Array<BaseContract.Model> {
        return arrayOf(preferencesModelImpl)
    }
}