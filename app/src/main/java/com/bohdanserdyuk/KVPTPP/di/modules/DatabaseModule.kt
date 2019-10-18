package com.bohdanserdyuk.KVPTPP.di.modules

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.model.db.LocalDatabase
import com.bohdanserdyuk.KVPTPP.model.db.ServicesDatabase
import com.bohdanserdyuk.KVPTPP.model.db.impl.ServicesDatabaseImpl
import com.lucky_apps.data.db.ServicesEntityDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideLocalDatabase(context: Context): LocalDatabase {
        return Room.databaseBuilder(context,
            LocalDatabase::class.java, "database").addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                context.resources.getStringArray(R.array.services_array).forEachIndexed { index, s ->
                    val values = ContentValues()
                    values.put("id", index)
                    values.put("title", s)
                    values.put("money", 0)
                    db.insert("serviceData", SQLiteDatabase.CONFLICT_REPLACE, values)
                }
            }
        }).build()
    }

    @Provides
    fun servicesDAO(localDatabase: LocalDatabase): ServicesEntityDAO {
        return localDatabase.servicesEntityDao()
    }

    @Provides
    fun servicesDatabase(dao: ServicesEntityDAO): ServicesDatabase {
        return ServicesDatabaseImpl(dao)
    }
}