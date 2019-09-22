package com.bohdanserdyuk.KVPTPP.di

import android.content.Context
import android.content.SharedPreferences
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.impl.PreferencesModel

internal interface DependencyInjector {
    fun sharedPreferences(context: Context): SharedPreferences

    fun mainPresenter(preferencesModel: PreferencesModel): BaseContract.MainPresenter
    fun paymentPresenter(preferencesModel: PreferencesModel): BaseContract.PaymentPresenter
    fun splashPresenter(preferencesModel: PreferencesModel): BaseContract.SplashPresenter
    fun oncePresenter(preferencesModel: PreferencesModel): BaseContract.OncePresenter

    fun emptyModelsArray(): Array<BaseContract.Model>
    fun preferencesModelArray(preferencesModel: PreferencesModel): Array<BaseContract.Model>
    fun settingsPresenter(preferencesModel: PreferencesModel): BaseContract.PreferencesPresenter

}