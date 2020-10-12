package com.firstems.erp.api.model.request.progress;

import com.google.gson.annotations.SerializedName;

public class ProgressProductDetail {
    @SerializedName("COMPCODE")
    private String compcode;

    @SerializedName("LCTNCODE")
    private String lctncode;

    @SerializedName("PRDCCODE")
    private String prdccode;

    @SerializedName("ERROQTTY")
    private double erroqtty;

    @SerializedName("ERROCODE")
    private String errocode;

    @SerializedName("PRDCQTTY")
    private double prdcqtty;

    public ProgressProductDetail() {
    }

    public void setLctncode(String lctncode) {
        this.lctncode = lctncode;
    }


    public void setPrdccode(String prdccode) {
        this.prdccode = prdccode;
    }

    public void setErroqtty(double erroqtty) {
        this.erroqtty = erroqtty;
    }

    public void setCompcode(String compcode) {
        this.compcode = compcode;
    }

    public void setErrocode(String errocode) {
        this.errocode = errocode;
    }

    public void setPrdcqtty(double prdcqtty) {
        this.prdcqtty = prdcqtty;
    }

    public String getCompcode() {
        return compcode;
    }

    public String getLctncode() {
        return lctncode;
    }

    public String getPrdccode() {
        return prdccode;
    }

    public double getErroqtty() {
        return erroqtty;
    }

    public String getErrocode() {
        return errocode;
    }

    public double getPrdcqtty() {
        return prdcqtty;
    }
}
