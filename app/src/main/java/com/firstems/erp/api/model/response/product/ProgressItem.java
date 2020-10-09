package com.firstems.erp.api.model.response.product;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProgressItem implements Serializable {
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
}
