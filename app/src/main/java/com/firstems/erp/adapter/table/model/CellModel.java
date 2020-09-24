package com.firstems.erp.adapter.table.model;

import com.evrencoskun.tableview.sort.ISortableModel;

/**
 * Created by Nguyen Quoc Cuong on 7/27/2020.
 */
public class CellModel implements ISortableModel {
    private String mId;
    private Object mData;

    public CellModel() {
    }

    public CellModel(String pId, Object mData) {
        this.mId = pId;
        this.mData = mData;
    }

    public Object getData() {
        return mData;
    }

    @Override
    public String getId() {
        return mId;
    }

    @Override
    public Object getContent() {
        return mData;
    }

}

