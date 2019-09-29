package com.bohdanserdyuk.KVPTPP.model.db

import com.bohdanserdyuk.KVPTPP.model.entity.ServiceData

interface ServicesDatabase {
    fun read(id: Int): ServiceData?

    fun readAll(): List<ServiceData>

    fun update(t: ServiceData)
}