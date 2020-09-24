package com.firstems.erp.api.model.response.advance_proposal_form;

import com.firstems.erp.api.model.response.ApiResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AdvanceProposalAddNewResponse extends ApiResponse implements Serializable {
    @SerializedName("RETNDATA")
    private List<AdvanceProposalAddNewResponseItem> AdvanceProposalAddNewResponseItem;
    
    public AdvanceProposalAddNewResponse() {
    }
    
    public List<com.firstems.erp.api.model.response.advance_proposal_form.AdvanceProposalAddNewResponseItem> getAdvanceProposalAddNewResponseItem() {
        return AdvanceProposalAddNewResponseItem;
    }
    
    public void setAdvanceProposalAddNewResponseItem(List<com.firstems.erp.api.model.response.advance_proposal_form.AdvanceProposalAddNewResponseItem> advanceProposalAddNewResponseItem) {
        AdvanceProposalAddNewResponseItem = advanceProposalAddNewResponseItem;
    }
}
