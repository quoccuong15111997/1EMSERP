package com.firstems.erp.api.model.response.askpermistion;import com.google.gson.annotations.SerializedName;import java.io.Serializable;public class AskPermistionUpdateItem implements Serializable {    @SerializedName("KKKK0000")    private String keyCode;        public AskPermistionUpdateItem() {    }        public String getKeyCode() {        return keyCode;    }        public void setKeyCode(String keyCode) {        this.keyCode = keyCode;    }}