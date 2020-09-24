package com.firstems.erp.api.model.response.signature;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Quoc Cuong on 8/1/2020.
 */
public class SignatureItemApiResponse implements Serializable,Comparable,Cloneable {
    @SerializedName("LABLCODE")
    private String labelCode;
    @SerializedName("LCTNCODE")
    private String locationCode;
    @SerializedName("DCMNCODE")
    private String dcmnCode;
    @SerializedName("DCMNNAME")
    private String dcmnName;
    @SerializedName("MAINCODE")
    private String mainCode;
    @SerializedName("MAINDATE")
    private String mainDate;
    @SerializedName("EMPLCODE")
    private String empCode;
    @SerializedName("NOTETEXT")
    private String note;
    @SerializedName("VLUE_FLD")
    private double valueFld;
    @SerializedName("VLUELABL")
    private String valueName;
    @SerializedName("UOM_EXPR")
    private String uomExpr;
    @SerializedName("ADD_VCHR")
    private int addVchr;
    @SerializedName("KEY_CODE")
    private String keyCode;
    @SerializedName("STTENAME")
    private String statusName;
    @SerializedName("STTESIGN")
    private int sTTESIGN;

    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }
    
    public int getsTTESIGN() {
        return sTTESIGN;
    }
    
    public void setsTTESIGN(int sTTESIGN) {
        this.sTTESIGN = sTTESIGN;
    }
    
    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public SignatureItemApiResponse() {
    }

    public String getLabelCode() {
        return labelCode;
    }

    public void setLabelCode(String labelCode) {
        this.labelCode = labelCode;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getDcmnName() {
        return dcmnName;
    }

    public void setDcmnName(String dcmnName) {
        this.dcmnName = dcmnName;
    }

    public String getMainCode() {
        return mainCode;
    }

    public void setMainCode(String mainCode) {
        this.mainCode = mainCode;
    }

    public String getMainDate() {
        return mainDate;
    }

    public void setMainDate(String mainDate) {
        this.mainDate = mainDate;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getValueFld() {
        return valueFld;
    }

    public void setValueFld(double valueFld) {
        this.valueFld = valueFld;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public String getUomExpr() {
        return uomExpr;
    }

    public void setUomExpr(String uomExpr) {
        this.uomExpr = uomExpr;
    }

    public int getAddVchr() {
        return addVchr;
    }

    public void setAddVchr(int addVchr) {
        this.addVchr = addVchr;
    }

    public String getDcmnCode() {
        return dcmnCode;
    }

    public void setDcmnCode(String dcmnCode) {
        this.dcmnCode = dcmnCode;
    }
    
    @Override
    public int compareTo(Object o) {
        SignatureItemApiResponse compare = (SignatureItemApiResponse) o;
        if (compare.keyCode.equals(this.keyCode) && compare.statusName.equals(this.statusName) && compare.sTTESIGN == this.sTTESIGN && compare.valueFld == this.valueFld){
            return 0;
        }
        return 1;
    }
    
    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

