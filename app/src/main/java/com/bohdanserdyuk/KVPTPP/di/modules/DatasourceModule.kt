package com.bohdanserdyuk.KVPTPP.di.modules

import com.bohdanserdyuk.KVPTPP.model.db.ServicesDatabase
import com.bohdanserdyuk.KVPTPP.model.repository.datasources.DatabaseServicesDatasource
import com.bohdanserdyuk.KVPTPP.model.repository.datasources.impl.DatabaseServicesDatasourceImpl
import dagger.Module
import dagger.Provides

@Module
class DatasourceModule {
    @Provides
    fun provideDatabaseServicesDatasource(database: ServicesDatabase): DatabaseServicesDatasource {
        return DatabaseServicesDatasourceImpl(database)
    }
}