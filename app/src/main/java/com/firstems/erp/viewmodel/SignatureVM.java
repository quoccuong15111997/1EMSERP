package com.firstems.erp.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;




public class SignatureVM extends BaseObservable {
    private String title;
    private int number;
    private int itemCode;

    public SignatureVM() {
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public SignatureVM(String title, int number, int itemCode) {
        this.title = title;
        this.number = number;
        this.itemCode = itemCode;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Bindable
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
