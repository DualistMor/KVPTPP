package com.bohdanserdyuk.KVPTPP.model.db;


import com.bohdanserdyuk.KVPTPP.model.entity.ServiceData;
import com.lucky_apps.data.db.ServicesEntityDAO;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ServiceData.class}, version = 1)
public abstract class LocalDatabase extends RoomDatabase {
    public abstract ServicesEntityDAO servicesEntityDao();
}
