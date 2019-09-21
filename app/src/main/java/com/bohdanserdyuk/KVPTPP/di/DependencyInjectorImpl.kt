package com.bohdanserdyuk.KVPTPP.di

import android.content.Context
import android.content.SharedPreferences
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.impl.PreferencesModel
import com.bohdanserdyuk.KVPTPP.presenter.impl.MainPresenterImpl
import com.bohdanserdyuk.KVPTPP.presenter.impl.OncePresenterImpl
import com.bohdanserdyuk.KVPTPP.presenter.impl.PaymentPresenterImpl
import com.bohdanserdyuk.KVPTPP.presenter.impl.SplashPresenterImpl

internal class DependencyInjectorImpl : DependencyInjector {

    override fun sharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(context.getString(R.string.preferences_location), Context.MODE_PRIVATE)
    }

    override fun mainPresenter(preferencesModel: PreferencesModel): BaseContract.MainPresenter {
       return MainPresenterImpl(preferencesModel)
    }

    override fun paymentPresenter(preferencesModel: PreferencesModel): BaseContract.PaymentPresenter {
        return PaymentPresenterImpl(preferencesModel)
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