package com.bohdanserdyuk.KVPTPP.model.repository.datasources.impl

import com.bohdanserdyuk.KVPTPP.model.db.ServicesDatabase
import com.bohdanserdyuk.KVPTPP.model.entity.ServiceData
import com.bohdanserdyuk.KVPTPP.model.repository.datasources.DatabaseServicesDatasource
import javax.inject.Inject

class DatabaseServicesDatasourceImpl @Inject constructor(val servicesDatabase: ServicesDatabase) : DatabaseServicesDatasource {
    override fun read(id: Int): ServiceData? {
        return servicesDatabase.read(id)
    }

    override fun readAll(): List<ServiceData> {
        return servicesDatabase.readAll()
    }

    override fun update(t: ServiceData) {
        servicesDatabase.update(t)
    }

}