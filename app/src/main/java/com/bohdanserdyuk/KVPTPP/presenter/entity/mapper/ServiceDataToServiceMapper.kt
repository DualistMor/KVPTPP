package com.bohdanserdyuk.KVPTPP.model.entity.mapper

import com.bohdanserdyuk.KVPTPP.model.entity.ServiceData
import com.bohdanserdyuk.KVPTPP.presenter.entity.Service

class ServiceDataToServiceMapper {
    fun mapServiceDataToService(serviceData: ServiceData): Service {
        return with(serviceData) { Service(id, title, if (money != 0) money.toString() else "") }
    }

    fun mapServiceDataToService(serviceData: List<ServiceData>): List<Service> {
        return List(serviceData.size) { mapServiceDataToService(serviceData[it]) }
    }

    fun mapServiceToServiceData(service: Service): ServiceData {
        return with(service) { ServiceData(id, title, if (money != "") Integer.parseInt(money) else 0) }
    }
}