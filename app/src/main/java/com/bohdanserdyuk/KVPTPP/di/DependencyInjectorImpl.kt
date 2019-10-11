package com.bohdanserdyuk.KVPTPP.di

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.db.LocalDatabase
import com.bohdanserdyuk.KVPTPP.model.repository.PreferencesModel
import com.bohdanserdyuk.KVPTPP.model.repository.ServicesModel
import com.bohdanserdyuk.KVPTPP.model.repository.impl.PreferencesModelImpl
import com.bohdanserdyuk.KVPTPP.presenter.impl.*

internal class DependencyInjectorImpl : DependencyInjector {

    override fun preferencesModelArray(preferencesModel: PreferencesModel): Array<BaseContract.Model> {
        return arrayOf(preferencesModel)
    }
}