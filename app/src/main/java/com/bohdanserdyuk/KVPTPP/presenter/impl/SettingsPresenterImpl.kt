package com.bohdanserdyuk.KVPTPP.presenter.impl

import android.os.Handler
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.impl.PreferencesModel
import com.bohdanserdyuk.KVPTPP.presenter.BasePresenter
import com.bohdanserdyuk.KVPTPP.view.impl.MainActivity
import com.bohdanserdyuk.KVPTPP.view.impl.OnceShowingScreen

class SettingsPresenterImpl(val preferencesModel: PreferencesModel): BasePresenter<BaseContract.PreferencesView>(), BaseContract.PreferencesPresenter {

    override fun onCreate() {
        super.onCreate()
        view.setVersionName()

        view.setDisplayHomeAsUpEnabled(true)
        view.setDisplayShowHomeEnabled(true)
    }

    override fun startWebsiteClicked() {
        view.startWebsite()
    }

    override fun sendFeedbackClicked() {
        view.sendFeedback()
    }

    override fun onBackButtonPressed() {
        view.goBack()
    }

    override fun initModels(): Array<BaseContract.Model> {
        return dependencyInjector.preferencesModelArray(preferencesModel)
    }
}