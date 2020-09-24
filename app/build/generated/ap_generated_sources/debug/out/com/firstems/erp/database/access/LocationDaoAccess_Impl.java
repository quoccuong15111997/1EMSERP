package com.firstems.erp.database.access;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.firstems.erp.database.model.LocationDBModel;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class LocationDaoAccess_Impl implements LocationDaoAccess {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfLocationDBModel;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  private final SharedSQLiteStatement __preparedStmtOfUpdateLocation;

  private final SharedSQLiteStatement __preparedStmtOfSetFalseAllCheck;

  public LocationDaoAccess_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLocationDBModel = new EntityInsertionAdapter<LocationDBModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `LocationDBModel`(`ID`,`LCTCODE`,`LCTNAME`,`COMPCODE`,`ISCHECK`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, LocationDBModel value) {
        stmt.bindLong(1, value.getId());
        if (value.getLocationCode() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getLocationCode());
        }
        if (value.getLocationName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getLocationName());
        }
        if (value.getCompanyCode() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getCompanyCode());
        }
        final int _tmp;
        _tmp = value.isCheck() ? 1 : 0;
        stmt.bindLong(5, _tmp);
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM LocationDBModel";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateLocation = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE LocationDBModel SET ISCHECK =? WHERE COMPCODE =? AND LCTCODE =?";
        return _query;
      }
    };
    this.__preparedStmtOfSetFalseAllCheck = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE LocationDBModel SET ISCHECK = 0";
        return _query;
      }
    };
  }

  @Override
  public void insertSingelLocation(LocationDBModel locationDBModel) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfLocationDBModel.insert(locationDBModel);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertMultiLocation(List<LocationDBModel> locationDBModels) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfLocationDBModel.insert(locationDBModels);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public void updateLocation(String comCode, String lctCode, boolean isCheck) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateLocation.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      final int _tmp;
      _tmp = isCheck ? 1 : 0;
      _stmt.bindLong(_argIndex, _tmp);
      _argIndex = 2;
      if (comCode == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, comCode);
      }
      _argIndex = 3;
      if (lctCode == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, lctCode);
      }
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateLocation.release(_stmt);
    }
  }

  @Override
  public void setFalseAllCheck() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetFalseAllCheck.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetFalseAllCheck.release(_stmt);
    }
  }

  @Override
  public List<LocationDBModel> getAllLocation() {
    final String _sql = "SELECT * FROM LocationDBModel";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("ID");
      final int _cursorIndexOfLocationCode = _cursor.getColumnIndexOrThrow("LCTCODE");
      final int _cursorIndexOfLocationName = _cursor.getColumnIndexOrThrow("LCTNAME");
      final int _cursorIndexOfCompanyCode = _cursor.getColumnIndexOrThrow("COMPCODE");
      final int _cursorIndexOfIsCheck = _cursor.getColumnIndexOrThrow("ISCHECK");
      final List<LocationDBModel> _result = new ArrayList<LocationDBModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final LocationDBModel _item;
        _item = new LocationDBModel();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpLocationCode;
        _tmpLocationCode = _cursor.getString(_cursorIndexOfLocationCode);
        _item.setLocationCode(_tmpLocationCode);
        final String _tmpLocationName;
        _tmpLocationName = _cursor.getString(_cursorIndexOfLocationName);
        _item.setLocationName(_tmpLocationName);
        final String _tmpCompanyCode;
        _tmpCompanyCode = _cursor.getString(_cursorIndexOfCompanyCode);
        _item.setCompanyCode(_tmpCompanyCode);
        final boolean _tmpIsCheck;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsCheck);
        _tmpIsCheck = _tmp != 0;
        _item.setCheck(_tmpIsCheck);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<LocationDBModel> getLocationByCode(String comCode, String lctCode) {
    final String _sql = "SELECT * FROM LocationDBModel WHERE COMPCODE =? AND LCTCODE =?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (comCode == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, comCode);
    }
    _argIndex = 2;
    if (lctCode == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, lctCode);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("ID");
      final int _cursorIndexOfLocationCode = _cursor.getColumnIndexOrThrow("LCTCODE");
      final int _cursorIndexOfLocationName = _cursor.getColumnIndexOrThrow("LCTNAME");
      final int _cursorIndexOfCompanyCode = _cursor.getColumnIndexOrThrow("COMPCODE");
      final int _cursorIndexOfIsCheck = _cursor.getColumnIndexOrThrow("ISCHECK");
      final List<LocationDBModel> _result = new ArrayList<LocationDBModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final LocationDBModel _item;
        _item = new LocationDBModel();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpLocationCode;
        _tmpLocationCode = _cursor.getString(_cursorIndexOfLocationCode);
        _item.setLocationCode(_tmpLocationCode);
        final String _tmpLocationName;
        _tmpLocationName = _cursor.getString(_cursorIndexOfLocationName);
        _item.setLocationName(_tmpLocationName);
        final String _tmpCompanyCode;
        _tmpCompanyCode = _cursor.getString(_cursorIndexOfCompanyCode);
        _item.setCompanyCode(_tmpCompanyCode);
        final boolean _tmpIsCheck;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsCheck);
        _tmpIsCheck = _tmp != 0;
        _item.setCheck(_tmpIsCheck);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
