package com.firstems.erp.callback.location;

import com.firstems.erp.database.model.LocationDBModel;

import java.util.List;

public interface InsertLocationCallback {
    void onSuccess(List<LocationDBModel>locationDBModels);
}
