package com.firstems.erp.api.model.response.signature.switchshift;

import com.firstems.erp.common.Util;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/21/2020.
 */
public class SwitchShiftItem implements Serializable {
    @SerializedName("LCTNCODE")
    private String locateCode;
    @SerializedName("MAINCODE")
    private String mainCode;
    @SerializedName("MAINDATE")
    private String mainDate;
    @SerializedName("ACCERGHT")
    private int accessRight;
    @SerializedName("NOTETEXT")
    private String noteText;
    @SerializedName("STTESIGN")
    private int sttEsign;
    @SerializedName("STTENAME")
    private String statusName;
    @SerializedName("CHGESLRY")
    private int checked;
    @SerializedName("DETAIL")
    private List<SwitchShiftDetail> switchShiftDetails;

    public SwitchShiftItem() {
    }

    public List<SwitchShiftDetail> getSwitchShiftDetails() {
        return switchShiftDetails;
    }

    public void setSwitchShiftDetails(List<SwitchShiftDetail> switchShiftDetails) {
        this.switchShiftDetails = switchShiftDetails;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public String getLocateCode() {
        return locateCode;
    }

    public void setLocateCode(String locateCode) {
        this.locateCode = locateCode;
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

    public int getAccessRight() {
        return accessRight;
    }

    public void setAccessRight(int accessRight) {
        this.accessRight = accessRight;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public int getSttEsign() {
        return sttEsign;
    }

    public void setSttEsign(int sttEsign) {
        this.sttEsign = sttEsign;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
    public String getMainDateDisplay(){
        return Util.formatDate(mainDate);
    }
}
