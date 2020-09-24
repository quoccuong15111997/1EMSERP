package com.firstems.erp.database.access;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.firstems.erp.database.model.LocationDBModel;

import java.util.List;

@Dao
public interface LocationDaoAccess {
    @Query("SELECT * FROM LocationDBModel")
    List<LocationDBModel> getAllLocation();
    @Query("SELECT * FROM LocationDBModel WHERE COMPCODE =:comCode AND LCTCODE =:lctCode")
    List<LocationDBModel> getLocationByCode(String comCode, String lctCode);
    @Query("DELETE FROM LocationDBModel")
    void deleteAll();
    @Insert
    void insertSingelLocation(LocationDBModel locationDBModel);
    @Insert
    void insertMultiLocation(List<LocationDBModel> locationDBModels);
    @Query("UPDATE LocationDBModel SET ISCHECK =:isCheck WHERE COMPCODE =:comCode AND LCTCODE =:lctCode")
    void updateLocation(String comCode, String lctCode, boolean isCheck);
    @Query("UPDATE LocationDBModel SET ISCHECK = 0")
    void setFalseAllCheck();
}
