package com.bohdanserdyuk.KVPTPP.presenter.impl

import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.repository.impl.PreferencesModelImpl
import com.bohdanserdyuk.KVPTPP.presenter.BasePresenter
import com.bohdanserdyuk.KVPTPP.presenter.interactor.use_case.CorrectFullNameUseCase

class OncePresenterImpl(val preferencesModelImpl: PreferencesModelImpl) : BasePresenter<BaseContract.OnceView>(), BaseContract.OncePresenter {

    override fun buttonClicked(pib: String) {
        if (CorrectFullNameUseCase().isFullNameCorrectUA(pib)) {
            getModel(PreferencesModelImpl::class.java).pib = pib
            view.startMainActivity()
        } else {
            view.showWrongPibToast()
        }
    }

    override fun initModels(): Array<BaseContract.Model> {
        return dependencyInjector.preferencesModelArray(preferencesModelImpl)
    }
}