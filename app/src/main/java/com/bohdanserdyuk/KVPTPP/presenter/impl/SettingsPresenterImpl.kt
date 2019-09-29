package com.bohdanserdyuk.KVPTPP.presenter.impl

import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.repository.impl.PreferencesModelImpl
import com.bohdanserdyuk.KVPTPP.presenter.BasePresenter
import com.bohdanserdyuk.KVPTPP.presenter.interactor.use_case.CorrectFullNameUseCase

class SettingsPresenterImpl(val preferencesModelImpl: PreferencesModelImpl): BasePresenter<BaseContract.PreferencesView>(), BaseContract.PreferencesPresenter {

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

    override fun initModels(): Array<BaseContract.Model> {
        return dependencyInjector.preferencesModelArray(preferencesModelImpl)
    }
}