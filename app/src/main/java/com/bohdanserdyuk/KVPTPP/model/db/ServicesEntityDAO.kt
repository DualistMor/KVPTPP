package com.lucky_apps.data.db

import androidx.room.*
import com.bohdanserdyuk.KVPTPP.model.entity.ServiceData

/**
 * Returns null for objects which all fields equals null
 */
@Dao
interface ServicesEntityDAO {

    @Insert
    fun create(t: ServiceData)

    @Query("SELECT * FROM ServiceData WHERE id = :id")
    fun read(id: Int): ServiceData?

    @Query("SELECT * FROM ServiceData")
    fun readAll(): List<ServiceData>

    @Update
    fun update(t: ServiceData)

    @Delete
    fun delete(t: ServiceData)
}