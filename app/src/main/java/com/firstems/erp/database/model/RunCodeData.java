package com.firstems.erp.database.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.security.PublicKey;

/**
 * Created by Nguyen Quoc Cuong on 8/6/2020.
 */
@Entity
public class RunCodeData implements Serializable {
    @NonNull
    @PrimaryKey()
    @ColumnInfo(name = "ID")
    public String id;
    @ColumnInfo(name = "RUNCODE")
    public String runCode;
    @ColumnInfo(name = "STATUS")
    public int status = -1;
    @ColumnInfo(name = "COMPANY")
    public String company;
    @ColumnInfo(name = "LOCATION")
    public String location;
    @ColumnInfo(name = "DATA")
    public String data;

    public RunCodeData() {
    }

    public RunCodeData(String runCode, int status) {
        this.runCode = runCode;
        this.status = status;
    }

    public RunCodeData(@NonNull String id, String runCode, int status, String data) {
        this.id = id;
        this.runCode = runCode;
        this.status = status;
        this.data = data;
    }

    public RunCodeData(@NonNull String id, String runCode, int status, String company, String location, String data) {
        this.id = id;
        this.runCode = runCode;
        this.status = status;
        this.company = company;
        this.location = location;
        this.data = data;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getRunCode() {
        return runCode;
    }

    public void setRunCode(String runCode) {
        this.runCode = runCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
