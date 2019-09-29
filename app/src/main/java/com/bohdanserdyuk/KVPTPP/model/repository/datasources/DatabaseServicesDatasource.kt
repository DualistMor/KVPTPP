package com.bohdanserdyuk.KVPTPP.model.repository.datasources

import com.bohdanserdyuk.KVPTPP.model.entity.ServiceData

interface DatabaseServicesDatasource {
    fun read(id: Int): ServiceData?

    fun readAll(): List<ServiceData>

    fun update(t: ServiceData)
}