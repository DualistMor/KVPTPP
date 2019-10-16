package com.bohdanserdyuk.KVPTPP.presenter.impl

import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.presenter.BasePresenter
import javax.inject.Inject

class RequestPresenterImpl @Inject constructor(model: BaseContract.RequestModel) : BasePresenter<BaseContract.RequestView, BaseContract.RequestModel>(model), BaseContract.RequestPresenter {
    override fun onCreateView() {
        view.loadPage(R.string.request_url)
        view.fillSupportActionBar(R.string.menu_request, true)
    }

    override fun pageFinished() {
        view.hideProgressBar()
    }
}