package com.bohdanserdyuk.KVPTPP.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.repository.PreferencesModel
import com.bohdanserdyuk.KVPTPP.model.repository.ServicesModel
import com.bohdanserdyuk.KVPTPP.model.repository.datasources.DatabaseServicesDatasource
import com.bohdanserdyuk.KVPTPP.model.repository.impl.PreferencesModelImpl
import com.bohdanserdyuk.KVPTPP.model.repository.impl.ServicesModelImpl
import dagger.Module
import dagger.Provides

@Module
class ModelsModule {
    @Provides
    fun provideServicesRepository(datasource: DatabaseServicesDatasource): ServicesModel {
        return ServicesModelImpl(datasource)
    }

    @Provides
    fun providePreferencesRepository(context: Context, pref: SharedPreferences, edit: SharedPreferences.Editor): PreferencesModel {
        return PreferencesModelImpl(context, pref, edit)
    }

    @Provides
    fun provideSplashModel(preferencesModel: PreferencesModel): BaseContract.SplashModel {
        return object : BaseContract.SplashModel {
            override val models: Array<BaseContract.Model>
                get() = arrayOf(preferencesModel)
        }
    }

    @Provides
    fun provideOnceModel(preferencesModel: PreferencesModel): BaseContract.OnceModel {
        return object : BaseContract.OnceModel {
            override val models: Array<BaseContract.Model>
                get() = arrayOf(preferencesModel)
        }
    }

    @Provides
    fun provideServicesModel(preferencesModel: PreferencesModel, servicesModel: ServicesModel): BaseContract.ServicesModel {
        return object : BaseContract.ServicesModel {
            override val models: Array<BaseContract.Model>
                get() = arrayOf(preferencesModel, servicesModel)
        }
    }

    @Provides
    fun provideMainModel(): BaseContract.MainModel {
        return object : BaseContract.MainModel {
            override val models: Array<BaseContract.Model>
                get() = emptyArray()
        }
    }

    @Provides
    fun provideAboutModel(): BaseContract.AboutModel {
        return object : BaseContract.AboutModel {
            override val models: Array<BaseContract.Model>
                get() = emptyArray()
        }
    }

    @Provides
    fun providePaymentModel(preferencesModel: PreferencesModel, servicesModel: ServicesModel): BaseContract.PaymentModel {
        return object : BaseContract.PaymentModel {
            override val models: Array<BaseContract.Model>
                get() = arrayOf(preferencesModel, servicesModel)
        }
    }

    @Provides
    fun provideSettingsModel(preferencesModel: PreferencesModel): BaseContract.SettingsModel {
        return object : BaseContract.SettingsModel {
            override val models: Array<BaseContract.Model>
                get() = arrayOf(preferencesModel)
        }
    }
}