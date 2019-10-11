package com.bohdanserdyuk.KVPTPP.model.repository

import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.entity.ServiceData

interface ServicesModel: BaseContract.Model {
    suspend fun read(id: Int): ServiceData?

    suspend fun readAll(): List<ServiceData>

    suspend fun update(t: ServiceData)
}