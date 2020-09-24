package com.firstems.erp.database.querypara;

import java.io.Serializable;

/**
 * Created by Nguyen Quoc Cuong on 8/5/2020.
 */
public class CompParameter implements Serializable {
    private String compCode;
    private String locationCode;

    public CompParameter() {
    }

    public String getCompCode() {
        return compCode;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }
}
