package com.firstems.erp.callback;

import com.firstems.erp.api.model.response.signature.SignatureItemApiResponse;

/**
 * Created by Nguyen Quoc Cuong on 8/1/2020.
 */
public interface SignatureItemClickCallback {
    void ItemClick(int position, SignatureItemApiResponse signatureItemApiResponse);
}
