package com.firstems.erp.api.model.response.askpermistion;

import com.firstems.erp.api.model.response.ApiResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/25/2020.
 */
public class AskPermistionApiResponse extends ApiResponse {
    @SerializedName("RETNDATA")
    private List<AskPermistionHeader> askPermistionHeaders;

    public AskPermistionApiResponse() {
    }

    public List<AskPermistionHeader> getAskPermistionHeaders() {
        return askPermistionHeaders;
    }

    public void setAskPermistionHeaders(List<AskPermistionHeader> askPermistionHeaders) {
        this.askPermistionHeaders = askPermistionHeaders;
    }
}
