package com.bohdanserdyuk.KVPTPP.presenter.impl

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.broker.ScrolledDown
import com.bohdanserdyuk.KVPTPP.broker.ScrolledUp
import com.bohdanserdyuk.KVPTPP.broker.StartPayment
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.entity.mapper.ServiceDataToServiceMapper
import com.bohdanserdyuk.KVPTPP.model.repository.PreferencesModel
import com.bohdanserdyuk.KVPTPP.model.repository.ServicesModel
import com.bohdanserdyuk.KVPTPP.model.repository.impl.PreferencesModelImpl
import com.bohdanserdyuk.KVPTPP.model.repository.impl.ServicesModelImpl
import com.bohdanserdyuk.KVPTPP.presenter.BasePresenter
import com.bohdanserdyuk.KVPTPP.presenter.entity.Service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class ServicesPresenterImpl @Inject constructor(model: BaseContract.ServicesModel) : BasePresenter<BaseContract.ServicesView, BaseContract.ServicesModel>(model), BaseContract.ServicesPresenter {

    lateinit var services: List<Service>
    lateinit var servicesModel: ServicesModel

    override fun onCreateView() {
        servicesModel = getModel(ServicesModel::class.java)
        getModel(PreferencesModel::class.java).isNewUser = false
        GlobalScope.launch(Dispatchers.Main) {
            services = ServiceDataToServiceMapper().mapServiceDataToService(servicesModel.readAll())
            view.setAdapter(services)
        }.start()
        view.fillSupportActionBar(R.string.menu_services, false)
    }

    override fun scrolledDown() = view.sendMessage(ScrolledDown())

    override fun scrolledUp() = view.sendMessage(ScrolledUp())

    override fun itemClick(service: Service) {
        GlobalScope.launch(Dispatchers.Main) {
            servicesModel.update(ServiceDataToServiceMapper().mapServiceToServiceData(service))
            getModel(PreferencesModelImpl::class.java).selectedService = service.id
        }.start()

        view.sendMessage(StartPayment())
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        GlobalScope.launch(Dispatchers.Main) {
            services.forEach {
                servicesModel.update(ServiceDataToServiceMapper().mapServiceToServiceData(it))
            }
        }.start()
    }
}