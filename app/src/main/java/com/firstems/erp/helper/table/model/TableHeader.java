package com.firstems.erp.helper.table.model;

/**
 * Created by Nguyen Quoc Cuong on 7/27/2020.
 */
public class TableHeader {
    private String headerName;
    private int order;

    public TableHeader() {
    }

    public TableHeader(String headerName, int order) {
        this.headerName = headerName;
        this.order = order;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
