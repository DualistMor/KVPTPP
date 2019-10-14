package com.bohdanserdyuk.KVPTPP.model.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "serviceData")
data class ServiceData(var title: String, var money: Float) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

   @Ignore constructor(id: Int, title: String, money: Float) : this(title, money) {
        this.id = id
    }
}
