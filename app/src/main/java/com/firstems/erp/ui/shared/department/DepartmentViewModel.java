package com.firstems.erp.ui.shared.department;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firstems.erp.api.model.response.department.DepartmentApiResponse;
import com.firstems.erp.api.model.response.department.DepartmentItemApiResponse;
import com.firstems.erp.callback.data.ConvertJsonCallback;
import com.firstems.erp.callback.data.DataApiCallback;
import com.firstems.erp.callback.data.DataSourceProviderCallback;
import com.firstems.erp.callback.data.LoadApiCallback;
import com.firstems.erp.common.Constant;
import com.firstems.erp.data.DataConvertProvider;
import com.firstems.erp.data.DataNetworkProvider;
import com.firstems.erp.data.DataSourceProvider;

import java.util.List;

public class DepartmentViewModel extends ViewModel {
    private MutableLiveData<List<DepartmentItemApiResponse>> mutableLiveData;
    public DepartmentViewModel() {
        mutableLiveData= new MutableLiveData<>();

        loadData();
    }

    private void loadData() {
        DataSourceProvider.getInstance().getDataSource(Constant.RUN_CODE_DEPARTMENT, "", new LoadApiCallback() {
            @Override
            public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                DataNetworkProvider.getInstance().getDepartmentList(dataApiCallback);
            }

            @Override
            public void onApiLoadFail() {

            }
        }, new DataSourceProviderCallback() {
            @Override
            public void onDataSource(String data) {
                getData(data);
            }

            @Override
            public void onUpdateImage(int status) {

            }
        });
    }

    private void getData(String data) {
        DataConvertProvider.getInstance().convertJsonToObject(data, new DepartmentApiResponse(), new ConvertJsonCallback() {
            @Override
            public void onConvertSuccess(Object obj) {
                DepartmentApiResponse departmentApiResponse  = (DepartmentApiResponse) obj;
                mutableLiveData.setValue(departmentApiResponse.getDepartmentItemApiResponses());
            }
        });
    }

    public LiveData<List<DepartmentItemApiResponse>> getMutableLiveData() {
        return mutableLiveData;
    }
}