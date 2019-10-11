package com.bohdanserdyuk.KVPTPP.model.repository

import com.bohdanserdyuk.KVPTPP.contract.BaseContract

interface PreferencesModel: BaseContract.Model {
    var isNewUser: Boolean
    var pib: String
    var selectedService: Int

    fun set(k: String, v: Long)

    fun get(k: String, def: Long): Long

    fun set(k: String, v: Int)

    fun get(k: String, def: Int): Int

    fun set(k: String, v: Boolean)

    fun get(k: String, def: Boolean): Boolean

    fun set(k: String, v: String)

    fun get(k: String, def: String): String

    fun set(k: String, v: Float)

    fun get(k: String, def: Float): Float

    fun remove(k: String)
}