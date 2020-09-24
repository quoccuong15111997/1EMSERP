package com.firstems.erp.api.model.request.advance_proposal_form;

import com.firstems.erp.api.model.response.advance_proposal_form.AdvanceProposalFormHeader;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AdvanceProposalFormRequest implements Serializable {
    @SerializedName("HEADER")
    private List<AdvanceProposalFormHeader> advanceProposalFormHeaders;
    
    public AdvanceProposalFormRequest(List<AdvanceProposalFormHeader> advanceProposalFormHeaders) {
        this.advanceProposalFormHeaders = advanceProposalFormHeaders;
    }
    public AdvanceProposalFormRequest() {
    }
    
    public List<AdvanceProposalFormHeader> getAdvanceProposalFormHeaders() {
        return advanceProposalFormHeaders;
    }
    
    public void setAdvanceProposalFormHeaders(List<AdvanceProposalFormHeader> advanceProposalFormHeaders) {
        this.advanceProposalFormHeaders = advanceProposalFormHeaders;
    }
}
