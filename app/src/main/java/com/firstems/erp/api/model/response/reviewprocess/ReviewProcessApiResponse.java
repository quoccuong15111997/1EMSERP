package com.firstems.erp.api.model.response.reviewprocess;

import com.firstems.erp.api.model.response.ApiResponse;
import com.firstems.erp.api.model.response.approved.info.ApproveInfoDetailApiResponse;
import com.firstems.erp.api.model.response.approved.info.file.ApproveIncludeFileApiResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/18/2020.
 */
public class ReviewProcessApiResponse extends ApiResponse implements Serializable {
    @SerializedName("RETNDATA")
    private List<ReviewProcessItem> reviewProcessItems;

    public ReviewProcessApiResponse() {
    }

    public List<ReviewProcessItem> getReviewProcessItems() {
        return reviewProcessItems;
    }

    public void setReviewProcessItems(List<ReviewProcessItem> reviewProcessItems) {
        this.reviewProcessItems = reviewProcessItems;
    }
}
