package com.bohdanserdyuk.KVPTPP.model.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.bohdanserdyuk.KVPTPP.model.entity.ServiceData;
import com.lucky_apps.data.db.ServicesEntityDAO;

@Database(entities = {ServiceData.class}, version = 1)
public abstract class LocalDatabase extends RoomDatabase {
    public abstract ServicesEntityDAO servicesEntityDao();
}
