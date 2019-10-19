package com.bohdanserdyuk.KVPTPP.di.modules

import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.entity.mapper.ServiceDataToServiceMapper
import com.bohdanserdyuk.KVPTPP.presenter.impl.*
import dagger.Module
import dagger.Provides

@Module
class PresentersModule {
    @Provides
    fun provideMainPresenter(model: BaseContract.MainModel): BaseContract.MainPresenter {
        return MainPresenterImpl(model)
    }

    @Provides
    fun provideServicesPresenter(model: BaseContract.ServicesModel): BaseContract.ServicesPresenter {
        return ServicesPresenterImpl(model)
    }

    @Provides
    fun provideSplashPresenter(model: BaseContract.SplashModel): BaseContract.SplashPresenter {
        return SplashPresenterImpl(model)
    }

    @Provides
    fun provideOncePresenter(model: BaseContract.OnceModel): BaseContract.OncePresenter {
        return OncescreenPresenterImpl(model)
    }

    @Provides
    fun providePaymentPresenter(model: BaseContract.PaymentModel, mapper: ServiceDataToServiceMapper): BaseContract.PaymentPresenter {
        return PaymentPresenterImpl(model, mapper)
    }

    @Provides
    fun provideRequestPresenter(model: BaseContract.RequestModel): BaseContract.RequestPresenter {
        return RequestPresenterImpl(model)
    }

    @Provides
    fun provideSettingsPresenter(model: BaseContract.SettingsModel): BaseContract.SettingsPresenter {
        return SettingsPresenterImpl(model)
    }

    @Provides
    fun provideAboutPresenter(model: BaseContract.AboutModel): BaseContract.AboutPresenter {
        return AboutPresenterImpl(model)
    }
}