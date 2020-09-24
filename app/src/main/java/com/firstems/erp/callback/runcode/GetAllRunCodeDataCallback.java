package com.firstems.erp.callback.runcode;

import com.firstems.erp.database.model.RunCodeData;

import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/11/2020.
 */
public interface GetAllRunCodeDataCallback  {
    void onGetRunCodeData(List<RunCodeData> runCodeDatas);
}
