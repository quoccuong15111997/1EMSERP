package com.firstems.erp.callback.location;

import com.firstems.erp.database.model.LocationDBModel;

import java.util.List;

public interface GetAllLocationCallbackl {
    void onLoadSuccess(List<LocationDBModel> locationDBModelList);
}
