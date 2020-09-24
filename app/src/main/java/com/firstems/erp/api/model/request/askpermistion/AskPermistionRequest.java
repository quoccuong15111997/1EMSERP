package com.firstems.erp.api.model.request.askpermistion;

import com.firstems.erp.api.model.response.askpermistion.AskPermistionHeader;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/29/2020.
 */
public class AskPermistionRequest implements Serializable {
    @SerializedName("HEADER")
    private List<AskPermistionHeader> askPermistionHeaders;

    public AskPermistionRequest() {
    }

    public List<AskPermistionHeader> getAskPermistionHeaders() {
        return askPermistionHeaders;
    }

    public void setAskPermistionHeaders(List<AskPermistionHeader> askPermistionHeaders) {
        this.askPermistionHeaders = askPermistionHeaders;
    }
}
