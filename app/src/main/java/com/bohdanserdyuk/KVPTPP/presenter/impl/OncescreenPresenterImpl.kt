package com.bohdanserdyuk.KVPTPP.presenter.impl

import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.impl.PreferencesModel
import com.bohdanserdyuk.KVPTPP.presenter.BasePresenter

class OncePresenterImpl(val preferencesModel: PreferencesModel): BasePresenter<BaseContract.OnceView>(), BaseContract.OncePresenter {

    override fun buttonClicked(pib: String) {
        getModel(PreferencesModel::class.java).pib = pib
        view.startMainActivity()
    }

    override fun initModels(): Array<BaseContract.Model> {
        return dependencyInjector.preferencesModelArray(preferencesModel)
    }
}