package com.bohdanserdyuk.KVPTPP.di.modules

import android.content.Context
import com.bohdanserdyuk.KVPTPP.model.repository.ServicesModel
import com.bohdanserdyuk.KVPTPP.model.repository.datasources.DatabaseServicesDatasource
import com.bohdanserdyuk.KVPTPP.model.repository.impl.ServicesModelImpl
import dagger.Module
import dagger.Provides

@Module
class ModelsModule {
    @Provides
    fun provideServicesModel(datasource: DatabaseServicesDatasource): ServicesModel {
        return ServicesModelImpl(datasource)
    }
}