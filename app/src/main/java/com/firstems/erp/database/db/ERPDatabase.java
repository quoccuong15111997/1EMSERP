package com.firstems.erp.database.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.firstems.erp.database.access.ERPDaoAccess;
import com.firstems.erp.database.access.LocationDaoAccess;
import com.firstems.erp.database.model.LocationDBModel;
import com.firstems.erp.database.model.RunCodeData;

/**
 * Created by Nguyen Quoc Cuong on 8/4/2020.
 */
@Database(entities = {RunCodeData.class, LocationDBModel.class},version = 1,exportSchema = false)
public abstract class ERPDatabase extends RoomDatabase {
    public abstract ERPDaoAccess erpDaoAccess();
    public abstract LocationDaoAccess locationDaoAccess();
}
