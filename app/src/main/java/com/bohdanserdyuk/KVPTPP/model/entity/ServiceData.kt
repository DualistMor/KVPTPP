package com.bohdanserdyuk.KVPTPP.model.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "serviceData")
data class ServiceData(var title: String, var amount: Float) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @Ignore
    constructor(id: Int, title: String, amount: Float) : this(title, amount) {
        this.id = id
    }
}
