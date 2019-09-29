package com.bohdanserdyuk.KVPTPP.di.modules

import android.content.Context
import com.bohdanserdyuk.KVPTPP.model.db.ServicesDatabase
import com.bohdanserdyuk.KVPTPP.model.repository.datasources.DatabaseServicesDatasource
import com.bohdanserdyuk.KVPTPP.model.repository.datasources.impl.DatabaseServicesDatasourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class DatasourceModule {
    @Provides
    fun provideDatabaseServicesDatasource(database: ServicesDatabase): DatabaseServicesDatasource {
        return DatabaseServicesDatasourceImpl(database)
    }
}