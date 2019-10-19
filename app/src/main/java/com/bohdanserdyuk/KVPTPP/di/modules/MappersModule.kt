package com.bohdanserdyuk.KVPTPP.di.modules

import com.bohdanserdyuk.KVPTPP.model.entity.mapper.ServiceDataToServiceMapper
import com.bohdanserdyuk.KVPTPP.view.helpers.FragmentGetActivityHelper
import dagger.Module
import dagger.Provides

@Module
class MappersModule {
    @Provides
    fun provideServiceDataToServiceMapper(): ServiceDataToServiceMapper {
        return ServiceDataToServiceMapper()
    }
}