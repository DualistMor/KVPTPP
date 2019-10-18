package com.bohdanserdyuk.KVPTPP.model.db.impl

import com.bohdanserdyuk.KVPTPP.model.db.ServicesDatabase
import com.bohdanserdyuk.KVPTPP.model.entity.ServiceData
import com.lucky_apps.data.db.ServicesEntityDAO
import javax.inject.Inject

class ServicesDatabaseImpl @Inject constructor(val servicesEntityDao: ServicesEntityDAO) : ServicesDatabase {
    override fun read(id: Int): ServiceData? {
        return servicesEntityDao.read(id)
    }

    override fun readAll(): List<ServiceData> {
        return servicesEntityDao.readAll()
    }

    override fun update(s: ServiceData) {
        servicesEntityDao.update(s)
    }
}