package com.firstems.erp.database.db;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.firstems.erp.database.access.ERPDaoAccess;
import com.firstems.erp.database.access.ERPDaoAccess_Impl;
import com.firstems.erp.database.access.LocationDaoAccess;
import com.firstems.erp.database.access.LocationDaoAccess_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class ERPDatabase_Impl extends ERPDatabase {
  private volatile ERPDaoAccess _eRPDaoAccess;

  private volatile LocationDaoAccess _locationDaoAccess;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `RunCodeData` (`ID` TEXT NOT NULL, `RUNCODE` TEXT, `STATUS` INTEGER NOT NULL, `COMPANY` TEXT, `LOCATION` TEXT, `DATA` TEXT, PRIMARY KEY(`ID`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `LocationDBModel` (`ID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `LCTCODE` TEXT, `LCTNAME` TEXT, `COMPCODE` TEXT, `ISCHECK` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"ba209da6131058e56935420e24bf1c32\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `RunCodeData`");
        _db.execSQL("DROP TABLE IF EXISTS `LocationDBModel`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsRunCodeData = new HashMap<String, TableInfo.Column>(6);
        _columnsRunCodeData.put("ID", new TableInfo.Column("ID", "TEXT", true, 1));
        _columnsRunCodeData.put("RUNCODE", new TableInfo.Column("RUNCODE", "TEXT", false, 0));
        _columnsRunCodeData.put("STATUS", new TableInfo.Column("STATUS", "INTEGER", true, 0));
        _columnsRunCodeData.put("COMPANY", new TableInfo.Column("COMPANY", "TEXT", false, 0));
        _columnsRunCodeData.put("LOCATION", new TableInfo.Column("LOCATION", "TEXT", false, 0));
        _columnsRunCodeData.put("DATA", new TableInfo.Column("DATA", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRunCodeData = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRunCodeData = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRunCodeData = new TableInfo("RunCodeData", _columnsRunCodeData, _foreignKeysRunCodeData, _indicesRunCodeData);
        final TableInfo _existingRunCodeData = TableInfo.read(_db, "RunCodeData");
        if (! _infoRunCodeData.equals(_existingRunCodeData)) {
          throw new IllegalStateException("Migration didn't properly handle RunCodeData(com.firstems.erp.database.model.RunCodeData).\n"
                  + " Expected:\n" + _infoRunCodeData + "\n"
                  + " Found:\n" + _existingRunCodeData);
        }
        final HashMap<String, TableInfo.Column> _columnsLocationDBModel = new HashMap<String, TableInfo.Column>(5);
        _columnsLocationDBModel.put("ID", new TableInfo.Column("ID", "INTEGER", true, 1));
        _columnsLocationDBModel.put("LCTCODE", new TableInfo.Column("LCTCODE", "TEXT", false, 0));
        _columnsLocationDBModel.put("LCTNAME", new TableInfo.Column("LCTNAME", "TEXT", false, 0));
        _columnsLocationDBModel.put("COMPCODE", new TableInfo.Column("COMPCODE", "TEXT", false, 0));
        _columnsLocationDBModel.put("ISCHECK", new TableInfo.Column("ISCHECK", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLocationDBModel = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLocationDBModel = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLocationDBModel = new TableInfo("LocationDBModel", _columnsLocationDBModel, _foreignKeysLocationDBModel, _indicesLocationDBModel);
        final TableInfo _existingLocationDBModel = TableInfo.read(_db, "LocationDBModel");
        if (! _infoLocationDBModel.equals(_existingLocationDBModel)) {
          throw new IllegalStateException("Migration didn't properly handle LocationDBModel(com.firstems.erp.database.model.LocationDBModel).\n"
                  + " Expected:\n" + _infoLocationDBModel + "\n"
                  + " Found:\n" + _existingLocationDBModel);
        }
      }
    }, "ba209da6131058e56935420e24bf1c32", "7f08328eb4a5c286005ecb2e9f3d54e7");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "RunCodeData","LocationDBModel");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `RunCodeData`");
      _db.execSQL("DELETE FROM `LocationDBModel`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public ERPDaoAccess erpDaoAccess() {
    if (_eRPDaoAccess != null) {
      return _eRPDaoAccess;
    } else {
      synchronized(this) {
        if(_eRPDaoAccess == null) {
          _eRPDaoAccess = new ERPDaoAccess_Impl(this);
        }
        return _eRPDaoAccess;
      }
    }
  }

  @Override
  public LocationDaoAccess locationDaoAccess() {
    if (_locationDaoAccess != null) {
      return _locationDaoAccess;
    } else {
      synchronized(this) {
        if(_locationDaoAccess == null) {
          _locationDaoAccess = new LocationDaoAccess_Impl(this);
        }
        return _locationDaoAccess;
      }
    }
  }
}
