package com.bohdanserdyuk.KVPTPP.presenter.impl

import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.entity.mapper.ServiceDataToServiceMapper
import com.bohdanserdyuk.KVPTPP.model.repository.ServicesModel
import com.bohdanserdyuk.KVPTPP.model.repository.impl.PreferencesModelImpl
import com.bohdanserdyuk.KVPTPP.presenter.BasePresenter
import com.bohdanserdyuk.KVPTPP.presenter.entity.Service
import com.bohdanserdyuk.KVPTPP.view.impl.PaymentActivity
import com.bohdanserdyuk.KVPTPP.view.impl.SettingsActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainPresenterImpl(val preferencesModelImpl: PreferencesModelImpl, val servicesModel: ServicesModel) : BasePresenter<BaseContract.MainView>(), BaseContract.MainPresenter {

    override fun onCreate() {
        super.onCreate()
        getModel(PreferencesModelImpl::class.java).isNewUser = false
        GlobalScope.launch(Dispatchers.Main) {
            val a = servicesModel.readAll()
            println(a.size.toString())
            view.setAdapter(ServiceDataToServiceMapper().mapServiceDataToService(a)) }.start()
    }

    override fun itemClick(service: Service) {
        GlobalScope.launch(Dispatchers.Main) {
            getModel(PreferencesModelImpl::class.java).selectedService = service.id
        }.start()
        view.startActivity(PaymentActivity::class.java)
    }

    override fun editClick() {
        view.startActivity(SettingsActivity::class.java)
    }

    override fun initModels(): Array<BaseContract.Model> {
        return dependencyInjector.preferencesModelArray(preferencesModelImpl)
    }
}