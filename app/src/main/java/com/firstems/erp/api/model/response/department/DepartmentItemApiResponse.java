package com.firstems.erp.api.model.response.department;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Quoc Cuong on 8/4/2020.
 */
public class DepartmentItemApiResponse implements Serializable {
    @SerializedName("LISTCODE")
    private String listCode;
    @SerializedName("LISTINDX")
    private String listIndex;
    @SerializedName("ITEMTYPE")
    private String itemType;
    @SerializedName("ITEMCODE")
    private String itemCode;
    @SerializedName("ITEMNAME")
    private String itemName;
    @SerializedName("ITEMATTR")
    private String itemMatTr;
    @SerializedName("ITEMTREE")
    private String itemTree;

    private int check = 0;


    public DepartmentItemApiResponse() {
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public String getListCode() {
        return listCode;
    }

    public void setListCode(String listCode) {
        this.listCode = listCode;
    }

    public String getListIndex() {
        return listIndex;
    }

    public void setListIndex(String listIndex) {
        this.listIndex = listIndex;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemMatTr() {
        return itemMatTr;
    }

    public void setItemMatTr(String itemMatTr) {
        this.itemMatTr = itemMatTr;
    }

    public String getItemTree() {
        return itemTree;
    }

    public void setItemTree(String itemTree) {
        this.itemTree = itemTree;
    }
}
