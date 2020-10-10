package com.firstems.erp.api.model.response.product;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProgressProductDetailItem implements Serializable, Comparable {
    @SerializedName("ODERCODE")
    private final String odercode;

    @SerializedName("STEPCODE")
    private final String stepcode;

    @SerializedName("STEPNAME")
    private final String stepname;

    @SerializedName("PRDCCODE")
    private final String prdccode;

    @SerializedName("PRDCNAME")
    private final String prdcname;

    @SerializedName("PRDCQTTY")
    private final double prdcqtty;

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
        return 0;
    }
}
