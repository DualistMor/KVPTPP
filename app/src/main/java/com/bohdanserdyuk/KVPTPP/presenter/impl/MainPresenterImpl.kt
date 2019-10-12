package com.bohdanserdyuk.KVPTPP.presenter.impl

import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.presenter.BasePresenter
import javax.inject.Inject

class MainPresenterImpl @Inject constructor(model: BaseContract.MainModel): BasePresenter<BaseContract.MainView, BaseContract.MainModel>(model), BaseContract.MainPresenter {
    override fun itemSelected(id: Int) {
        view.animateChangeFragment(id)
    }
}