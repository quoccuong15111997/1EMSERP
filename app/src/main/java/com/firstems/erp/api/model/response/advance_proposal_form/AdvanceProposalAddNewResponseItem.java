package com.firstems.erp.api.model.response.advance_proposal_form;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AdvanceProposalAddNewResponseItem implements Serializable {
    @SerializedName("KKKK0000")
    private String keyCode;
    
    public AdvanceProposalAddNewResponseItem() {
    }
    
    public String getKeyCode() {
        return keyCode;
    }
    
    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }
}
