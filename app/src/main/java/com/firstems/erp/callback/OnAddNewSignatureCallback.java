package com.firstems.erp.callback;

import com.firstems.erp.api.model.response.signature.SignatureItemApiResponse;
import com.firstems.erp.viewmodel.SignatureVM;

public interface OnAddNewSignatureCallback {
    void onAddClick(SignatureItemApiResponse model);
}
