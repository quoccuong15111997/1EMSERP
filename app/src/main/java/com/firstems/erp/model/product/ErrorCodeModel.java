package com.firstems.erp.model.product;

import java.io.Serializable;

public class ErrorCodeModel implements Serializable {
    private String code;
    private String errorName;
    private boolean isCheck;

    public ErrorCodeModel(String code, String errorName, boolean isCheck) {
        this.code = code;
        this.errorName = errorName;
        this.isCheck = isCheck;
    }

    public ErrorCodeModel() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
