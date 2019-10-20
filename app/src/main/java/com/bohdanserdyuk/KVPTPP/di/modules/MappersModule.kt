package com.bohdanserdyuk.KVPTPP.di.modules

import com.bohdanserdyuk.KVPTPP.model.entity.mapper.ServiceDataToServiceMapper
import dagger.Module
import dagger.Provides

@Module
class MappersModule {
    @Provides
    fun provideServiceDataToServiceMapper(): ServiceDataToServiceMapper {
        return ServiceDataToServiceMapper()
    }
}