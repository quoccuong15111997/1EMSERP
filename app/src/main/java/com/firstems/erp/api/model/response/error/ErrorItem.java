package com.firstems.erp.api.model.response.error;

import com.google.gson.annotations.SerializedName;

public class ErrorItem {
    @SerializedName("LISTCODE")
    private String listcode;

    @SerializedName("LISTINDX")
    private String listindx;

    @SerializedName("ITEMTYPE")
    private String itemtype;

    @SerializedName("ITEMCODE")
    private final String itemcode;

    @SerializedName("ITEMNAME")
    private final String itemname;

    @SerializedName("ITEMATTR")
    private String itemattr;

    @SerializedName("ITEMTREE")
    private String itemtree;

    private boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public ErrorItem(String itemcode, String itemname) {
        this.itemcode = itemcode;
        this.itemname = itemname;
    }

    public ErrorItem(String listcode, String listindx, String itemtype, String itemcode,
                     String itemname, String itemattr, String itemtree) {
        this.listcode = listcode;
        this.listindx = listindx;
        this.itemtype = itemtype;
        this.itemcode = itemcode;
        this.itemname = itemname;
        this.itemattr = itemattr;
        this.itemtree = itemtree;
    }

    public String getListcode() {
        return listcode;
    }

    public String getListindx() {
        return listindx;
    }

    public String getItemtype() {
        return itemtype;
    }

    public String getItemcode() {
        return itemcode;
    }

    public String getItemname() {
        return itemname;
    }

    public String getItemattr() {
        return itemattr;
    }

    public String getItemtree() {
        return itemtree;
    }
}
