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

class RequestPresenterImpl @Inject constructor(model: BaseContract.RequestModel) : BasePresenter<BaseContract.RequestView, BaseContract.RequestModel>(model), BaseContract.RequestPresenter {
    override fun onCreateView() = view.loadPage(R.string.request_url)

    override fun pageFinished() {
        view.hideProgressBar()
    }
}