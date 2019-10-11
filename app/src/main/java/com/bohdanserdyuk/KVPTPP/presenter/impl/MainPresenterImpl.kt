package com.bohdanserdyuk.KVPTPP.presenter.impl

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.entity.mapper.ServiceDataToServiceMapper
import com.bohdanserdyuk.KVPTPP.model.repository.PreferencesModel
import com.bohdanserdyuk.KVPTPP.model.repository.ServicesModel
import com.bohdanserdyuk.KVPTPP.model.repository.impl.PreferencesModelImpl
import com.bohdanserdyuk.KVPTPP.model.repository.impl.ServicesModelImpl
import com.bohdanserdyuk.KVPTPP.presenter.BasePresenter
import com.bohdanserdyuk.KVPTPP.presenter.entity.Service
import com.bohdanserdyuk.KVPTPP.view.impl.PaymentActivity
import com.bohdanserdyuk.KVPTPP.view.impl.SettingsActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainPresenterImpl @Inject constructor(model: BaseContract.MainModel) : BasePresenter<BaseContract.MainView, BaseContract.MainModel>(model), BaseContract.MainPresenter {

    lateinit var services: List<Service>
    lateinit var servicesModel: ServicesModel

    override fun onCreate() {
        super.onCreate()

        servicesModel = getModel(ServicesModelImpl::class.java)
        getModel(PreferencesModelImpl::class.java).isNewUser = false

        GlobalScope.launch(Dispatchers.Main) {
            services = ServiceDataToServiceMapper().mapServiceDataToService(servicesModel.readAll())
            view.setAdapter(services)
        }.start()
    }

    override fun itemClick(service: Service) {
        GlobalScope.launch(Dispatchers.Main) {
            servicesModel.update(ServiceDataToServiceMapper().mapServiceToServiceData(service))
            getModel(PreferencesModelImpl::class.java).selectedService = service.id
        }.start()
        view.startActivity(PaymentActivity::class.java)
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_STOP)
    fun onStop() {
        GlobalScope.launch(Dispatchers.Main) {
            services.forEach {
                servicesModel.update(ServiceDataToServiceMapper().mapServiceToServiceData(it))
            }
        }.start()
    }

    override fun editClick() {
        view.startActivity(SettingsActivity::class.java)
    }
}