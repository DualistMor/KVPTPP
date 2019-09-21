package com.bohdanserdyuk.KVPTPP.presenter.impl

import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.presenter.BasePresenter

class MainPresenterImpl: BasePresenter<BaseContract.MainView>(), BaseContract.MainPresenter {

    override fun onCreate() {
        super.onCreate()
    }

    override fun itemClick(i: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun editClick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initModels(): Array<BaseContract.Model> {
        return dependencyInjector.emptyModelsArray()
    }
}