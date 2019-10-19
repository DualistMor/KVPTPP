package com.bohdanserdyuk.KVPTPP.presenter.impl

import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.entity.mapper.ServiceDataToServiceMapper
import com.bohdanserdyuk.KVPTPP.model.interactor.JsFormatterBuilder
import com.bohdanserdyuk.KVPTPP.model.repository.PreferencesModel
import com.bohdanserdyuk.KVPTPP.model.repository.ServicesModel
import com.bohdanserdyuk.KVPTPP.presenter.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class PaymentPresenterImpl @Inject constructor(model: BaseContract.PaymentModel, val mapper: ServiceDataToServiceMapper) : BasePresenter<BaseContract.PaymentView, BaseContract.PaymentModel>(model), BaseContract.PaymentPresenter {

    lateinit var preferencesModel: PreferencesModel

    override fun onCreateView() {
        view.loadPage(R.string.payment_url)
        view.fillSupportActionBar(R.string.payment, true)
        preferencesModel = getModel(PreferencesModel::class.java)
    }

    /**
     * The even index of vararg are jsPatterns and uneven - userPatterns
     */
    override fun pageFinished(vararg patterns: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val serv = mapper.mapServiceDataToService(getModel(ServicesModel::class.java).read(preferencesModel.selectedService)!!)
            view.loadPage(JsFormatterBuilder()
                .addFunction(patterns[0],
                    patterns[1], serv.money)
                .addFunction(patterns[2],
                    patterns[3],
                    serv.title,
                    preferencesModel.pib
                ).build()
            )
        }
        view.hideProgressBar()
    }
}