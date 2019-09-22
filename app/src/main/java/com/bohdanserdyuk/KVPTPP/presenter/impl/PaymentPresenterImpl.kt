package com.bohdanserdyuk.KVPTPP.presenter.impl

import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.impl.PreferencesModel
import com.bohdanserdyuk.KVPTPP.presenter.BasePresenter
import com.bohdanserdyuk.KVPTPP.presenter.interactor.use_case.JsFormatterUseCase

class PaymentPresenterImpl(val preferencesModel: PreferencesModel) : BasePresenter<BaseContract.PaymentView>(), BaseContract.PaymentPresenter {

    override fun onCreate() {
        super.onCreate()
        view.setDisplayHomeAsUpEnabled(true)
        view.setDisplayShowHomeEnabled(true)

        view.loadPage(R.string.payment_url)
    }

    override fun pageFinished(usersPattern: String, jsPattern: String) {
        val preferencesModel = getModel(PreferencesModel::class.java)
        view.loadPage(JsFormatterUseCase().formatJsWithUsersInfo(usersPattern, jsPattern, preferencesModel.selectedService, preferencesModel.pib))
        view.hideProgressBar()
    }

    override fun onBackButtonPressed() {
        view.goBack()
    }

    override fun initModels(): Array<BaseContract.Model> {
        return dependencyInjector.preferencesModelArray(preferencesModel)
    }
}