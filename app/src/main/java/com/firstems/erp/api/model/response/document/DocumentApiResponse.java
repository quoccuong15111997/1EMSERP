package com.firstems.erp.api.model.response.document;

import com.firstems.erp.api.model.response.ApiResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DocumentApiResponse extends ApiResponse implements Serializable {
    @SerializedName("RETNDATA")
    private List<DocumentItemApiResponse> documentItemApiResponses;
    
    public DocumentApiResponse() {
    }
    
    public List<DocumentItemApiResponse> getDocumentItemApiResponses() {
        return documentItemApiResponses;
    }
    
    public void setDocumentItemApiResponses(List<DocumentItemApiResponse> documentItemApiResponses) {
        this.documentItemApiResponses = documentItemApiResponses;
    }
}
