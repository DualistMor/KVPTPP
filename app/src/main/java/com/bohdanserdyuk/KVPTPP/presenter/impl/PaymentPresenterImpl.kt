package com.bohdanserdyuk.KVPTPP.presenter.impl

import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.entity.mapper.ServiceDataToServiceMapper
import com.bohdanserdyuk.KVPTPP.model.repository.ServicesModel
import com.bohdanserdyuk.KVPTPP.model.repository.impl.PreferencesModelImpl
import com.bohdanserdyuk.KVPTPP.presenter.BasePresenter
import com.bohdanserdyuk.KVPTPP.presenter.interactor.use_case.JsFormatterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PaymentPresenterImpl(val preferencesModelImpl: PreferencesModelImpl, val servicesModel: ServicesModel) : BasePresenter<BaseContract.PaymentView>(), BaseContract.PaymentPresenter {

    override fun onCreate() {
        super.onCreate()
        view.setDisplayHomeAsUpEnabled(true)
        view.setDisplayShowHomeEnabled(true)

        view.loadPage(R.string.payment_url)
    }

    override fun pageFinished(usersPattern: String, jsPattern: String) {
        val preferencesModel = getModel(PreferencesModelImpl::class.java)
        GlobalScope.launch(Dispatchers.Main) {
            view.loadPage(JsFormatterUseCase()
                .formatJsWithUsersInfo(usersPattern,
                    jsPattern,
                    ServiceDataToServiceMapper().mapServiceDataToService(servicesModel.read(preferencesModel.selectedService)!!).title,
                    preferencesModel.pib
                )
            )
        }
        view.hideProgressBar()
    }

    override fun onBackButtonPressed() {
        view.goBack()
    }

    override fun initModels(): Array<BaseContract.Model> {
        return dependencyInjector.preferencesModelArray(preferencesModelImpl)
    }
}