package com.firstems.erp.callback;

import com.firstems.erp.api.model.response.approved.ApprovedItemDetail_1;
import com.firstems.erp.api.model.response.approved.ApprovedItemDetail_2;

/**
 * Created by Nguyen Quoc Cuong on 8/27/2020.
 */
public interface AprrovedDetail_1_ClickListener {
    void onItemClick(ApprovedItemDetail_2 itemDetail_2, ApprovedItemDetail_1 approvedItemDetail_1);
}
