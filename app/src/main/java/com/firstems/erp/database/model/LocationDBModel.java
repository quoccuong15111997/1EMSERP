package com.firstems.erp.database.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class LocationDBModel implements Serializable {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private int id;
    @ColumnInfo(name = "LCTCODE")
    private String locationCode;
    @ColumnInfo(name = "LCTNAME")
    private String locationName;
    @ColumnInfo(name = "COMPCODE")
    private String companyCode;
    @ColumnInfo(name = "ISCHECK")
    private boolean isCheck;
    
    public LocationDBModel() {
    }
    
    public LocationDBModel(String locationCode, String locationName, String companyCode) {
        this.locationCode = locationCode;
        this.locationName = locationName;
        this.companyCode = companyCode;
    }
    
    public boolean isCheck() {
        return isCheck;
    }
    
    public void setCheck(boolean check) {
        isCheck = check;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getLocationCode() {
        return locationCode;
    }
    
    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }
    
    public String getLocationName() {
        return locationName;
    }
    
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
    
    public String getCompanyCode() {
        return companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}
