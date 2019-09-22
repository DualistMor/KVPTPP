package com.bohdanserdyuk.KVPTPP.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.impl.PreferencesModel
import com.bohdanserdyuk.KVPTPP.presenter.impl.*

internal class DependencyInjectorImpl : DependencyInjector {

    override fun sharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    override fun mainPresenter(preferencesModel: PreferencesModel): BaseContract.MainPresenter {
       return MainPresenterImpl(preferencesModel)
    }

    override fun paymentPresenter(preferencesModel: PreferencesModel): BaseContract.PaymentPresenter {
        return PaymentPresenterImpl(preferencesModel)
    }

    override fun settingsPresenter(preferencesModel: PreferencesModel): BaseContract.PreferencesPresenter {
        return SettingsPresenterImpl(preferencesModel)
    }

    override fun splashPresenter(preferencesModel: PreferencesModel): BaseContract.SplashPresenter {
        return SplashPresenterImpl(preferencesModel)
    }

    override fun oncePresenter(preferencesModel: PreferencesModel): BaseContract.OncePresenter {
        return OncePresenterImpl(preferencesModel)
    }

    override fun emptyModelsArray(): Array<BaseContract.Model> {
        return arrayOfNulls<BaseContract.Model>(0) as Array<BaseContract.Model>
    }

    override fun preferencesModelArray(preferencesModel: PreferencesModel): Array<BaseContract.Model> {
        return arrayOf(preferencesModel)
    }
}