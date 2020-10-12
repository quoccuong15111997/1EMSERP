package com.firstems.erp.api.model.response.product;

import android.graphics.Bitmap;

import com.firstems.erp.helper.barcode.BarCodeHelper;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProgressItem implements Serializable, Comparable {
    @SerializedName("CMMDCODE")
    private String cmmdcode;

    @SerializedName("CMMDDATE")
    private String cmmddate;

    @SerializedName("PCPDCODE")
    private String pcpdcode;

    @SerializedName("PCPDNAME")
    private String pcpdname;

    @SerializedName("DDDD")
    private String dddd;

    @SerializedName("ACCERGHT")
    private int accerght;

    @SerializedName("STTESIGN")
    private int sttesign;

    @SerializedName("STTENAME")
    private String sttename;

    @SerializedName("KKKK0000")
    private String kkkk0000;

    private boolean isCheck = false;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public ProgressItem(String cmmdcode, String cmmddate, String pcpdcode, String pcpdname, String dddd, int accerght, int sttesign, String sttename, String kkkk0000, boolean isCheck, Bitmap barCode) {
        this.cmmdcode = cmmdcode;
        this.cmmddate = cmmddate;
        this.pcpdcode = pcpdcode;
        this.pcpdname = pcpdname;
        this.dddd = dddd;
        this.accerght = accerght;
        this.sttesign = sttesign;
        this.sttename = sttename;
        this.kkkk0000 = kkkk0000;
        this.isCheck = isCheck;
        this.barCode = barCode;
    }

    public void setCmmdcode(String cmmdcode) {
        this.cmmdcode = cmmdcode;
    }

    public void setCmmddate(String cmmddate) {
        this.cmmddate = cmmddate;
    }

    public void setPcpdcode(String pcpdcode) {
        this.pcpdcode = pcpdcode;
    }

    public void setPcpdname(String pcpdname) {
        this.pcpdname = pcpdname;
    }

    public void setDddd(String dddd) {
        this.dddd = dddd;
    }

    public void setAccerght(int accerght) {
        this.accerght = accerght;
    }

    public void setSttesign(int sttesign) {
        this.sttesign = sttesign;
    }

    public void setSttename(String sttename) {
        this.sttename = sttename;
    }

    public void setKkkk0000(String kkkk0000) {
        this.kkkk0000 = kkkk0000;
    }

    public void setBarCode(Bitmap barCode) {
        this.barCode = barCode;
    }

    private Bitmap barCode;

    public Bitmap getBarCode() {
        return BarCodeHelper.getInstance().generateBarCode(getCmmdcode());
    }

    public ProgressItem() {
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
        if (compare.getCmmdcode().equals(this.cmmdcode) && compare.getPcpdcode().equals(this.pcpdcode) && compare.getSttesign() == this.getSttesign()) {
            return 0;
        } else
            return 1;
    }
}
