package com.firstems.erp.callback;

import com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeCT;

import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/20/2020.
 */
public interface GetDataTimeKeepingTCCallback {
    void onLoaded(List<TimekeepingTypeCT> timekeepingTypeCTS);
}
