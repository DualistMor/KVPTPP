package com.bohdanserdyuk.KVPTPP.presenter.impl

import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.presenter.BasePresenter
import com.bohdanserdyuk.KVPTPP.model.interactor.use_case.CorrectFullNameUseCase
import com.bohdanserdyuk.KVPTPP.model.repository.PreferencesModel
import javax.inject.Inject

class SettingsPresenterImpl @Inject constructor(model: BaseContract.SettingsModel) : BasePresenter<BaseContract.SettingsView, BaseContract.SettingsModel>(model), BaseContract.SettingsPresenter {

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

    override fun isFullNameCorrectUA(str: String): Boolean {
        return CorrectFullNameUseCase().isFullNameCorrectUA(str)
    }

    override fun onBackButtonPressed() {
        view.goBack()
    }
}