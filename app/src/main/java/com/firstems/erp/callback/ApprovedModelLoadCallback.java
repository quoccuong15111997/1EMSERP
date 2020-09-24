package com.firstems.erp.callback;

import androidx.lifecycle.MutableLiveData;

import com.firstems.erp.api.model.response.approved.info.ApproveInfoApiResponse;

/**
 * Created by Nguyen Quoc Cuong on 7/31/2020.
 */
public interface ApprovedModelLoadCallback {
    void onDataLoaded(MutableLiveData<ApproveInfoApiResponse> mutableLiveData);
    void onDataLoadFail(String failMessage);
    void onServerFail();
}
