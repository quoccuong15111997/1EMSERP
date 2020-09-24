package com.firstems.erp.database.access;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.firstems.erp.database.model.RunCodeData;

import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/4/2020.
 */
@Dao
public interface ERPDaoAccess {
    @Query("SELECT * FROM RunCodeData")
    List<RunCodeData> getAllRunCodeData();
    @Query("SELECT * FROM RunCodeData WHERE ID LIKE :id")
    List<RunCodeData> getRunCodeData(String id);
    @Query("UPDATE RunCodeData SET STATUS =:status WHERE ID LIKE :id")
    int updateStatus(String id, int status);
    @Query("UPDATE RunCodeData SET DATA =:data WHERE ID LIKE :id")
    int updateData(String id, String data);
    @Insert
    List<Long> saveRunCodeData(List<RunCodeData> runCodeData);
    @Insert
    Long saveSingelRunCodeData(RunCodeData runCode);
    @Query("SELECT * FROM runcodedata WHERE ID LIKE :runCodeID")
    List<RunCodeData> checkRunCodeIDExits(String runCodeID);
}
