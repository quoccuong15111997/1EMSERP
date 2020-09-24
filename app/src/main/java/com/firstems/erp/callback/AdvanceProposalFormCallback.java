package com.firstems.erp.callback;

import com.firstems.erp.api.model.response.advance_proposal_form.AdvanceProposalFormApiResponse;

public interface AdvanceProposalFormCallback {
    void onLoadSuccess(AdvanceProposalFormApiResponse advanceProposalFormApiResponse);
    void onLoadFail(String mess);
    void onServerFail();
}
