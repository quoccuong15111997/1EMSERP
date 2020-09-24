package com.firstems.erp.database.access;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.firstems.erp.database.model.RunCodeData;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class ERPDaoAccess_Impl implements ERPDaoAccess {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfRunCodeData;

  private final SharedSQLiteStatement __preparedStmtOfUpdateStatus;

  private final SharedSQLiteStatement __preparedStmtOfUpdateData;

  public ERPDaoAccess_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRunCodeData = new EntityInsertionAdapter<RunCodeData>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `RunCodeData`(`ID`,`RUNCODE`,`STATUS`,`COMPANY`,`LOCATION`,`DATA`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, RunCodeData value) {
        if (value.id == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.id);
        }
        if (value.runCode == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.runCode);
        }
        stmt.bindLong(3, value.status);
        if (value.company == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.company);
        }
        if (value.location == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.location);
        }
        if (value.data == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.data);
        }
      }
    };
    this.__preparedStmtOfUpdateStatus = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE RunCodeData SET STATUS =? WHERE ID LIKE ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateData = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE RunCodeData SET DATA =? WHERE ID LIKE ?";
        return _query;
      }
    };
  }

  @Override
  public List<Long> saveRunCodeData(List<RunCodeData> runCodeData) {
    __db.beginTransaction();
    try {
      List<Long> _result = __insertionAdapterOfRunCodeData.insertAndReturnIdsList(runCodeData);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Long saveSingelRunCodeData(RunCodeData runCode) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfRunCodeData.insertAndReturnId(runCode);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int updateStatus(String id, int status) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateStatus.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, status);
      _argIndex = 2;
      if (id == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, id);
      }
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateStatus.release(_stmt);
    }
  }

  @Override
  public int updateData(String id, String data) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateData.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (data == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, data);
      }
      _argIndex = 2;
      if (id == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, id);
      }
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateData.release(_stmt);
    }
  }

  @Override
  public List<RunCodeData> getAllRunCodeData() {
    final String _sql = "SELECT * FROM RunCodeData";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("ID");
      final int _cursorIndexOfRunCode = _cursor.getColumnIndexOrThrow("RUNCODE");
      final int _cursorIndexOfStatus = _cursor.getColumnIndexOrThrow("STATUS");
      final int _cursorIndexOfCompany = _cursor.getColumnIndexOrThrow("COMPANY");
      final int _cursorIndexOfLocation = _cursor.getColumnIndexOrThrow("LOCATION");
      final int _cursorIndexOfData = _cursor.getColumnIndexOrThrow("DATA");
      final List<RunCodeData> _result = new ArrayList<RunCodeData>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final RunCodeData _item;
        _item = new RunCodeData();
        _item.id = _cursor.getString(_cursorIndexOfId);
        _item.runCode = _cursor.getString(_cursorIndexOfRunCode);
        _item.status = _cursor.getInt(_cursorIndexOfStatus);
        _item.company = _cursor.getString(_cursorIndexOfCompany);
        _item.location = _cursor.getString(_cursorIndexOfLocation);
        _item.data = _cursor.getString(_cursorIndexOfData);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<RunCodeData> getRunCodeData(String id) {
    final String _sql = "SELECT * FROM RunCodeData WHERE ID LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("ID");
      final int _cursorIndexOfRunCode = _cursor.getColumnIndexOrThrow("RUNCODE");
      final int _cursorIndexOfStatus = _cursor.getColumnIndexOrThrow("STATUS");
      final int _cursorIndexOfCompany = _cursor.getColumnIndexOrThrow("COMPANY");
      final int _cursorIndexOfLocation = _cursor.getColumnIndexOrThrow("LOCATION");
      final int _cursorIndexOfData = _cursor.getColumnIndexOrThrow("DATA");
      final List<RunCodeData> _result = new ArrayList<RunCodeData>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final RunCodeData _item;
        _item = new RunCodeData();
        _item.id = _cursor.getString(_cursorIndexOfId);
        _item.runCode = _cursor.getString(_cursorIndexOfRunCode);
        _item.status = _cursor.getInt(_cursorIndexOfStatus);
        _item.company = _cursor.getString(_cursorIndexOfCompany);
        _item.location = _cursor.getString(_cursorIndexOfLocation);
        _item.data = _cursor.getString(_cursorIndexOfData);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<RunCodeData> checkRunCodeIDExits(String runCodeID) {
    final String _sql = "SELECT * FROM runcodedata WHERE ID LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (runCodeID == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, runCodeID);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("ID");
      final int _cursorIndexOfRunCode = _cursor.getColumnIndexOrThrow("RUNCODE");
      final int _cursorIndexOfStatus = _cursor.getColumnIndexOrThrow("STATUS");
      final int _cursorIndexOfCompany = _cursor.getColumnIndexOrThrow("COMPANY");
      final int _cursorIndexOfLocation = _cursor.getColumnIndexOrThrow("LOCATION");
      final int _cursorIndexOfData = _cursor.getColumnIndexOrThrow("DATA");
      final List<RunCodeData> _result = new ArrayList<RunCodeData>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final RunCodeData _item;
        _item = new RunCodeData();
        _item.id = _cursor.getString(_cursorIndexOfId);
        _item.runCode = _cursor.getString(_cursorIndexOfRunCode);
        _item.status = _cursor.getInt(_cursorIndexOfStatus);
        _item.company = _cursor.getString(_cursorIndexOfCompany);
        _item.location = _cursor.getString(_cursorIndexOfLocation);
        _item.data = _cursor.getString(_cursorIndexOfData);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
