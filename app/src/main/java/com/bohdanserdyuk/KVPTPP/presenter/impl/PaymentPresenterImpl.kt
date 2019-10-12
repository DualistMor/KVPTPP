package com.bohdanserdyuk.KVPTPP.presenter.impl

import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.entity.mapper.ServiceDataToServiceMapper
import com.bohdanserdyuk.KVPTPP.model.repository.ServicesModel
import com.bohdanserdyuk.KVPTPP.model.repository.impl.PreferencesModelImpl
import com.bohdanserdyuk.KVPTPP.presenter.BasePresenter
import com.bohdanserdyuk.KVPTPP.model.interactor.use_case.JsFormatterUseCase
import com.bohdanserdyuk.KVPTPP.model.repository.PreferencesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class PaymentPresenterImpl @Inject constructor(model: BaseContract.PaymentModel) : BasePresenter<BaseContract.PaymentView, BaseContract.PaymentModel>(model), BaseContract.PaymentPresenter {

    override fun onCreateView() {
        view.loadPage(R.string.payment_url)
    }

    override fun pageFinished(usersPattern: String, jsPattern: String) {
        val preferencesModel = getModel(PreferencesModelImpl::class.java)
        GlobalScope.launch(Dispatchers.Main) {
            view.loadPage(JsFormatterUseCase()
                .formatJsWithUsersInfo(usersPattern,
                    jsPattern,
                    ServiceDataToServiceMapper().mapServiceDataToService(getModel(ServicesModel::class.java).read(preferencesModel.selectedService)!!).title,
                    preferencesModel.pib
                )
            )
        }
        view.hideProgressBar()
    }
}