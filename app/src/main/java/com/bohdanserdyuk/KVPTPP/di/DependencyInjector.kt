package com.bohdanserdyuk.KVPTPP.di

import android.content.Context
import android.content.SharedPreferences
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.db.LocalDatabase
import com.bohdanserdyuk.KVPTPP.model.repository.PreferencesModel
import com.bohdanserdyuk.KVPTPP.model.repository.ServicesModel
import com.bohdanserdyuk.KVPTPP.model.repository.impl.PreferencesModelImpl

internal interface DependencyInjector {
    /**
     * ROOM
     */

    fun preferencesModelArray(preferencesModel: PreferencesModel): Array<BaseContract.Model>
}