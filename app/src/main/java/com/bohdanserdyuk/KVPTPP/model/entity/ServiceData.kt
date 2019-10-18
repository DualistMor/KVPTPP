package com.bohdanserdyuk.KVPTPP.model.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "serviceData")
data class ServiceData(var title: String, var money: Float) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @Ignore
    constructor(id: Int, title: String, money: Float) : this(title, money) {
        this.id = id
    }
}
