package com.firstems.erp.api.model.response.loai_de_nghi_tam_ung;import com.google.gson.annotations.SerializedName;import java.io.Serializable;/** * Created by Nguyen Quoc Cuong on 9/4/2020. */public class LoaiDoiTuongNhanItem implements Serializable {    @SerializedName("LISTCODE")    private String lISTCODE;    @SerializedName("LISTINDX")    private String lISTINDX;    @SerializedName("ITEMTYPE")    private String iTEMTYPE;    @SerializedName("ITEMCODE")    private String iTEMCODE;    @SerializedName("ITEMNAME")    private String iTEMNAME;    @SerializedName("ITEMATTR")    private String iTEMATTR;    @SerializedName("ITEMTREE")    private String iTEMTREE;    public LoaiDoiTuongNhanItem() {    }    public String getlISTCODE() {        return lISTCODE;    }    public void setlISTCODE(String lISTCODE) {        this.lISTCODE = lISTCODE;    }    public String getlISTINDX() {        return lISTINDX;    }    public void setlISTINDX(String lISTINDX) {        this.lISTINDX = lISTINDX;    }    public String getiTEMTYPE() {        return iTEMTYPE;    }    public void setiTEMTYPE(String iTEMTYPE) {        this.iTEMTYPE = iTEMTYPE;    }    public String getiTEMCODE() {        return iTEMCODE;    }    public void setiTEMCODE(String iTEMCODE) {        this.iTEMCODE = iTEMCODE;    }    public String getiTEMNAME() {        return iTEMNAME;    }    public void setiTEMNAME(String iTEMNAME) {        this.iTEMNAME = iTEMNAME;    }    public String getiTEMATTR() {        return iTEMATTR;    }    public void setiTEMATTR(String iTEMATTR) {        this.iTEMATTR = iTEMATTR;    }    public String getiTEMTREE() {        return iTEMTREE;    }    public void setiTEMTREE(String iTEMTREE) {        this.iTEMTREE = iTEMTREE;    }    @Override    public String toString() {        return iTEMNAME;    }}