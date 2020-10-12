package com.firstems.erp.api.model.response.product;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;

public class ProgressProductDetailItem implements Serializable, Comparable, Cloneable {
    @SerializedName("ODERCODE")
    private final String odercode;

    @SerializedName("STEPCODE")
    private String stepcode;

    @SerializedName("STEPNAME")
    private String stepname;

    @SerializedName("PRDCCODE")
    private String prdccode;

    @SerializedName("PRDCNAME")
    private String prdcname;

    @SerializedName("PRDCQTTY")
    private double prdcqtty;

    @SerializedName("ORGNCODE")
    private final int orgncode;

    @SerializedName("SORTCODE")
    private final int sortcode;

    @SerializedName("DDDD")
    private final String dddd;

    @SerializedName("ACCERGHT")
    private final int accerght;

    @SerializedName("STTESIGN")
    private final int sttesign;

    @SerializedName("STTENAME")
    private final String sttename;

    @SerializedName("KKKK0000")
    private final String kkkk0000;

    private long quatityGood = 0;
    private long quatityBad = 0;
    private String errorCode;
    private boolean isEdit = false;

    private ProgressStep progressStep;

    public ProgressStep getProgressStep() {
        return new ProgressStep(stepcode, stepname);
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    public long getQuatityGood() {
        return quatityGood;
    }

    public void setQuatityGood(long quatityGood) {
        this.quatityGood = quatityGood;
    }

    public long getQuatityBad() {
        return quatityBad;
    }

    public void setQuatityBad(long quatityBad) {
        this.quatityBad = quatityBad;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public ProgressProductDetailItem(String odercode, String stepcode, String stepname,
                                     String prdccode, String prdcname, double prdcqtty, int orgncode, int sortcode,
                                     String dddd, int accerght, int sttesign, String sttename, String kkkk0000) {
        this.odercode = odercode;
        this.stepcode = stepcode;
        this.stepname = stepname;
        this.prdccode = prdccode;
        this.prdcname = prdcname;
        this.prdcqtty = prdcqtty;
        this.orgncode = orgncode;
        this.sortcode = sortcode;
        this.dddd = dddd;
        this.accerght = accerght;
        this.sttesign = sttesign;
        this.sttename = sttename;
        this.kkkk0000 = kkkk0000;
    }

    public String getOdercode() {
        return odercode;
    }

    public String getStepcode() {
        return stepcode;
    }

    public String getStepname() {
        return stepname;
    }

    public String getPrdccode() {
        return prdccode;
    }

    public String getPrdcname() {
        return prdcname;
    }

    public double getPrdcqtty() {
        return prdcqtty;
    }

    public int getOrgncode() {
        return orgncode;
    }

    public int getSortcode() {
        return sortcode;
    }

    public String getDddd() {
        return dddd;
    }

    public int getAccerght() {
        return accerght;
    }

    public int getSttesign() {
        return sttesign;
    }

    public String getSttename() {
        return sttename;
    }

    public String getKkkk0000() {
        return kkkk0000;
    }

    @Override
    public int compareTo(Object o) {
        ProgressProductDetailItem compare = (ProgressProductDetailItem) o;
        if (compare.getPrdccode().equals(this.prdccode) && compare.getQuatityBad() == this.quatityBad && compare.getQuatityGood() == this.quatityGood && compare.getErrorCode().equals(this.errorCode)){
            return 0;
        }
        return 1;
    }

    @NonNull
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void setStepcode(String stepcode) {
        this.stepcode = stepcode;
    }

    public void setStepname(String stepname) {
        this.stepname = stepname;
    }

    public void setPrdccode(String prdccode) {
        this.prdccode = prdccode;
    }

    public void setPrdcname(String prdcname) {
        this.prdcname = prdcname;
    }

    public void setPrdcqtty(double prdcqtty) {
        this.prdcqtty = prdcqtty;
    }

    public void setProgressStep(ProgressStep progressStep) {
        this.progressStep = progressStep;
    }
}
