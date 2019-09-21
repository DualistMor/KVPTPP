package com.bohdanserdyuk.KVPTPP.presenter.impl

import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.impl.PreferencesModel
import com.bohdanserdyuk.KVPTPP.presenter.BasePresenter

class PaymentPresenterImpl(val preferencesModel: PreferencesModel): BasePresenter<BaseContract.PaymentView>(), BaseContract.PaymentPresenter {

    override fun pageFinished() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initModels(): Array<BaseContract.Model> {
        return dependencyInjector.preferencesModelArray(preferencesModel)
    }
}