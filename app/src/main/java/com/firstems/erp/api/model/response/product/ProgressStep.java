package com.firstems.erp.api.model.response.product;

import java.io.Serializable;

public class ProgressStep implements Serializable {
    private String stepCode;
    private String stepName;

    public ProgressStep(String stepCode, String stepName) {
        this.stepCode = stepCode;
        this.stepName = stepName;
    }

    public String getStepCode() {
        return stepCode;
    }

    public void setStepCode(String stepCode) {
        this.stepCode = stepCode;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    @Override
    public String toString() {
        return stepName;
    }
}
