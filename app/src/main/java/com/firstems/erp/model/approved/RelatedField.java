package com.firstems.erp.model.approved;

/**
 * Created by Nguyen Quoc Cuong on 8/15/2020.
 */
public class RelatedField {
    private String itemCode;
    private String itemName;

    public RelatedField(String itemCode, String itemName) {
        this.itemCode = itemCode;
        this.itemName = itemName;
    }

    public RelatedField() {
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

    @Override
    public String toString() {
        return itemName;
    }
}
