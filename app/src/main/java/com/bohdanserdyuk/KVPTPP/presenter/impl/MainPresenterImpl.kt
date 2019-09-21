package com.bohdanserdyuk.KVPTPP.presenter.impl

import android.util.Log
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.impl.PreferencesModel
import com.bohdanserdyuk.KVPTPP.presenter.BasePresenter
import com.bohdanserdyuk.KVPTPP.presenter.interactor.StringToAnyMapper
import com.bohdanserdyuk.KVPTPP.view.impl.PaymentActivity

class MainPresenterImpl(val preferencesModel: PreferencesModel): BasePresenter<BaseContract.MainView>(), BaseContract.MainPresenter {

    lateinit var servicesArray: Array<Any>

    override fun onCreate() {
        super.onCreate()
        view.changeToolbarTitle(R.string.services)
        getModel(PreferencesModel::class.java).isNewUser = false
        servicesArray = StringToAnyMapper().toAnyArray(view.getServices())
        view.setAdapter(servicesArray)
    }

    override fun itemClick(i: Int) {
        getModel(PreferencesModel::class.java).selectedService = servicesArray[i].toString()
        view.startActivity(PaymentActivity::class.java)
    }

    override fun editClick() {
        
    }

    override fun initModels(): Array<BaseContract.Model> {
        return dependencyInjector.preferencesModelArray(preferencesModel)
    }
}