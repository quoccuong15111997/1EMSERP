package com.firstems.erp.api.model.response.product;

import android.graphics.Bitmap;

import com.firstems.erp.helper.barcode.BarCodeHelper;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProgressItem implements Serializable, Comparable{
    @SerializedName("CMMDCODE")
    private final String cmmdcode;

    @SerializedName("CMMDDATE")
    private final String cmmddate;

    @SerializedName("PCPDCODE")
    private final String pcpdcode;

    @SerializedName("PCPDNAME")
    private final String pcpdname;

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

    private Bitmap barCode;

    public Bitmap getBarCode() {
        return BarCodeHelper.getInstance().generateBarCode(getCmmdcode());
    }

    public ProgressItem(String cmmdcode, String cmmddate, String pcpdcode, String pcpdname,
                        String dddd, int accerght, int sttesign, String sttename, String kkkk0000) {
        this.cmmdcode = cmmdcode;
        this.cmmddate = cmmddate;
        this.pcpdcode = pcpdcode;
        this.pcpdname = pcpdname;
        this.dddd = dddd;
        this.accerght = accerght;
        this.sttesign = sttesign;
        this.sttename = sttename;
        this.kkkk0000 = kkkk0000;
    }

    public String getCmmdcode() {
        return cmmdcode;
    }

    public String getCmmddate() {
        return cmmddate;
    }

    public String getPcpdcode() {
        return pcpdcode;
    }

    public String getPcpdname() {
        return pcpdname;
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
        ProgressItem compare = (ProgressItem) o;
        if (compare.getCmmdcode().equals(this.cmmdcode) && compare.getPcpdcode().equals(this.pcpdcode) && compare.getSttesign() == this.getSttesign()){
            return 0;
        }
        else
            return 1;
    }
}
