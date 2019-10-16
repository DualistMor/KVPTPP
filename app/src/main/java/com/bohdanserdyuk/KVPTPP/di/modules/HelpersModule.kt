package com.bohdanserdyuk.KVPTPP.di.modules

import android.content.Context
import com.bohdanserdyuk.KVPTPP.model.db.ServicesDatabase
import com.bohdanserdyuk.KVPTPP.model.repository.datasources.DatabaseServicesDatasource
import com.bohdanserdyuk.KVPTPP.model.repository.datasources.impl.DatabaseServicesDatasourceImpl
import com.bohdanserdyuk.KVPTPP.view.helpers.FragmentGetActivityHelper
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class HelpersModule {
    @Provides
    fun provideFragmentGetActivityHelper(): FragmentGetActivityHelper {
        return FragmentGetActivityHelper()
    }
}