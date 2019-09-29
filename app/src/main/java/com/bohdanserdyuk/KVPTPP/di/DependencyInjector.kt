package com.bohdanserdyuk.KVPTPP.di

import android.content.Context
import android.content.SharedPreferences
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.db.LocalDatabase
import com.bohdanserdyuk.KVPTPP.model.repository.ServicesModel
import com.bohdanserdyuk.KVPTPP.model.repository.impl.PreferencesModelImpl

internal interface DependencyInjector {
    fun sharedPreferences(context: Context): SharedPreferences

    fun mainPresenter(preferencesModelImpl: PreferencesModelImpl, servicesModel: ServicesModel): BaseContract.MainPresenter
    fun paymentPresenter(preferencesModelImpl: PreferencesModelImpl, servicesModel: ServicesModel): BaseContract.PaymentPresenter
    fun splashPresenter(preferencesModelImpl: PreferencesModelImpl): BaseContract.SplashPresenter
    fun oncePresenter(preferencesModelImpl: PreferencesModelImpl): BaseContract.OncePresenter

    /**
     * ROOM
     */
    fun roomLocalDatabase(context: Context): LocalDatabase

    fun emptyModelsArray(): Array<BaseContract.Model>
    fun preferencesModelArray(preferencesModelImpl: PreferencesModelImpl): Array<BaseContract.Model>
    fun settingsPresenter(preferencesModelImpl: PreferencesModelImpl): BaseContract.PreferencesPresenter

}