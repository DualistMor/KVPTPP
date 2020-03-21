package com.bohdanserdyuk.KVPTPP.presenter.impl

import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.repository.impl.PreferencesModelImpl
import com.bohdanserdyuk.KVPTPP.presenter.BasePresenter
import javax.inject.Inject

class OncescreenPresenterImpl @Inject constructor(model: BaseContract.OnceModel) : BasePresenter<BaseContract.OnceView, BaseContract.OnceModel>(model), BaseContract.OncePresenter {

    override fun buttonClicked(pib: String) {
        getModel(PreferencesModelImpl::class.java).pib = pib
        view.startMainActivity()
    }
}