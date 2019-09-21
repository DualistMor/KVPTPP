package com.bohdanserdyuk.KVPTPP.presenter.impl

import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.impl.PreferencesModel
import com.bohdanserdyuk.KVPTPP.presenter.BasePresenter

class MainPresenterImpl(val preferencesModel: PreferencesModel): BasePresenter<BaseContract.MainView>(), BaseContract.MainPresenter {

    override fun onCreate() {
        super.onCreate()
        view.changeToolbarTitle(R.string.services)
        getModel(PreferencesModel::class.java).isNewUser = false
    }

    override fun itemClick(i: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun editClick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initModels(): Array<BaseContract.Model> {
        return dependencyInjector.preferencesModelArray(preferencesModel)
    }
}