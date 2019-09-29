package com.bohdanserdyuk.KVPTPP.model.repository.impl

import com.bohdanserdyuk.KVPTPP.model.BaseModel
import com.bohdanserdyuk.KVPTPP.model.entity.ServiceData
import com.bohdanserdyuk.KVPTPP.model.repository.ServicesModel
import com.bohdanserdyuk.KVPTPP.model.repository.datasources.DatabaseServicesDatasource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ServicesModelImpl @Inject constructor(val servicesDatasource: DatabaseServicesDatasource) : BaseModel<ServicesModelImpl>(), ServicesModel {

    override suspend fun read(id: Int): ServiceData? {
        return withContext(Dispatchers.Default) { servicesDatasource.read(id) }
    }

    override suspend fun readAll(): List<ServiceData> {
        return withContext(Dispatchers.Default) { servicesDatasource.readAll() }
    }

    override suspend fun update(t: ServiceData) {
        GlobalScope.async { servicesDatasource.update(t) }
    }
}