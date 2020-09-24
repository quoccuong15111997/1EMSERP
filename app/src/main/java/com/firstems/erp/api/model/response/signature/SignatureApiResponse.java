package com.firstems.erp.api.model.response.signature;

import com.firstems.erp.api.model.response.ApiResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/1/2020.
 */
public class SignatureApiResponse extends ApiResponse implements Serializable {
    @SerializedName("RETNDATA")
    private List<SignatureItemApiResponse> signatureItemApiResponses;

    public SignatureApiResponse() {
    }

    public List<SignatureItemApiResponse> getSignatureItemApiResponses() {
        return signatureItemApiResponses;
    }

    public void setSignatureItemApiResponses(List<SignatureItemApiResponse> signatureItemApiResponses) {
        this.signatureItemApiResponses = signatureItemApiResponses;
    }
}
